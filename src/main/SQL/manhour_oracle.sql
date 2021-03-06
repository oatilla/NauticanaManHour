SPOOL D:\manhour.log

PROMPT "CREATE TABLE PROJECT"

CREATE TABLE PROJECT (
  PROJECT_ID          NUMBER(8)     NOT NULL,
  CAPTION             VARCHAR2(100) NOT NULL,
  STATUS              VARCHAR2(20)  DEFAULT 'INITIAL' NOT NULL,
  COUNTRY             CHAR(2)       NOT NULL,
  LOCATION            VARCHAR2(50),
  CUSTOMER            VARCHAR2(100), 
  CONTRACT_DATE       DATE,
  AREA_HANDOVER       DATE,
  DURATION            NUMBER(8,0),
  REVIZED_DURATION    NUMBER(8,0),
  DURATION_TYPE       CHAR(1),
  REVIZED_COMPLETION  DATE,
  EXPECTED_COMPLETION DATE,
  END_OF_WARRANTY     DATE,
  CONTRACTED_AMOUNT   NUMBER(12,0), 
  CONTRACT_EXCHANGE   VARCHAR2(3), 
  EXPECTED_COST       NUMBER(12,0), 
  ADVANCE_PERCENT     NUMBER(3,0), 
  LETTER_OF_ADVANCE   NUMBER(3,0), 
  LETTER_OF_WARRANTY  NUMBER(3,0), 
  ORGANIZATION_ID     NUMBER(8,0)
);

CREATE SEQUENCE PROJECT_ID_SEQ
  MINVALUE 1
  MAXVALUE 99999999
  START WITH 1
  INCREMENT BY 1;

ALTER TABLE PROJECT
  ADD CONSTRAINT PROJECT_PK
      PRIMARY KEY (PROJECT_ID);

ALTER TABLE PROJECT
  ADD CONSTRAINT PROJECT_CK1
      CHECK (AREA_HANDOVER IS NULL OR CONTRACT_DATE <= AREA_HANDOVER);


PROMPT "CREATE TABLE CATEGORY"

CREATE TABLE CATEGORY (
  CATEGORY_ID       NUMBER(8)      NOT NULL,
  PARENT_ID         NUMBER(8),
  CAPTION           VARCHAR2(100)  NOT NULL,
  CAT_INDEX         VARCHAR2(2)    NOT NULL,
  DETAILS           VARCHAR2(1000),
  UNIT              VARCHAR2(20),
  CAT_LEVEL         NUMBER(2)      NOT NULL,
  TREE_CODE         VARCHAR2(20)   NOT NULL,
  CBS_CODE          VARCHAR2(20),
  PROJECT_ID        NUMBER(8),
  MAIN_FLAG         CHAR(1)        NOT NULL,
  UNIT2             VARCHAR2(3)
);

CREATE SEQUENCE CATEGORY_ID_SEQ
  MINVALUE 1
  MAXVALUE 99999999
  START WITH 10000
  INCREMENT BY 1;

INSERT INTO CATEGORY (CATEGORY_ID, PARENT_ID, CAPTION, CAT_INDEX, DETAILS, UNIT, CAT_LEVEL, TREE_CODE, MAIN_FLAG)
SELECT DISTINCT CAT_HEADER_ID, PARENT_ID, CATEGORY_DESCRIPTION, CATEGORY_CODE, CATEGORY_DETAIL, BIRIM, CAT_LEVEL, CONCAT_CATEGORY_CODE, '1'
  FROM APPS.XXGAMA_ADSA_WBS_CATEGORY_V
 WHERE (CAT_HEADER_ID, CAT_LEVEL) IN
       (SELECT CAT_HEADER_ID, MAX(CAT_LEVEL) FROM APPS.XXGAMA_ADSA_WBS_CATEGORY_V GROUP BY CAT_HEADER_ID);

INSERT INTO CATEGORY (CATEGORY_ID, PARENT_ID, CAPTION, CAT_INDEX, DETAILS, UNIT, CAT_LEVEL, TREE_CODE, MAIN_FLAG)
SELECT DISTINCT CAT_HEADER_ID, PARENT_ID, CATEGORY_DESCRIPTION, CATEGORY_CODE, CATEGORY_DETAIL, BIRIM, CAT_LEVEL, CONCAT_CATEGORY_CODE, '2'
  FROM APPS.XXGAMA_ADSA_WBS_SEC_CATEGORY_V
 WHERE (CAT_HEADER_ID, CAT_LEVEL) IN
       (SELECT CAT_HEADER_ID, MAX(CAT_LEVEL) FROM APPS.XXGAMA_ADSA_WBS_SEC_CATEGORY_V GROUP BY CAT_HEADER_ID);

--INSERT INTO CATEGORY (CATEGORY_ID, CAPTION, CAT_INDEX, CAT_LEVEL, TREE_CODE, MAIN_FLAG) VALUES (0, 'KAYIP ����L�K', '99', 1, '99', '0');

ALTER TABLE CATEGORY
  ADD CONSTRAINT CATEGORY_PK
      PRIMARY KEY (CATEGORY_ID);

ALTER TABLE CATEGORY
  ADD CONSTRAINT CATEGORY_FK1
      FOREIGN KEY (PARENT_ID)
      REFERENCES CATEGORY (CATEGORY_ID);



PROMPT "CREATE TABLE CATEGORY_TEXT"

CREATE TABLE CATEGORY_TEXT (
  CATEGORY_ID       NUMBER(8)      NOT NULL,
  LANGCODE          CHAR(2)        NOT NULL,
  CAPTION           VARCHAR2(100)  NOT NULL
);

ALTER TABLE CATEGORY_TEXT
  ADD CONSTRAINT CATEGORY_TEXT_PK
      PRIMARY KEY (CATEGORY_ID, LANGCODE);

ALTER TABLE CATEGORY_TEXT
  ADD CONSTRAINT CATEGORY_TEXT_FK1
      FOREIGN KEY (CATEGORY_ID)
      REFERENCES CATEGORY (CATEGORY_ID)
      ON DELETE CASCADE;



PROMPT "CREATE TABLE SUBCONTRACTOR"

CREATE TABLE SUBCONTRACTOR (
  SUBCONTRACTOR_ID     NUMBER(8)     NOT NULL,
  CAPTION              VARCHAR2(100) NOT NULL,
  EXT_SUBCONTRACTOR    VARCHAR2(50)
);


ALTER TABLE SUBCONTRACTOR
  ADD CONSTRAINT SUBCONTRACTOR_PK
      PRIMARY KEY (SUBCONTRACTOR_ID);

CREATE SEQUENCE SUBCONTRACTOR_ID_SEQ
  MINVALUE 10000
  MAXVALUE 99999999
  START WITH 10000
  INCREMENT BY 1;



PROMPT "CREATE TABLE WORKER"

CREATE TABLE WORKER (
  WORKER_ID          NUMBER(8)     NOT NULL,
  CAPTION            VARCHAR2(100) NOT NULL,
  PERSON_ID          NUMBER(8),
  SUBCONTRACTOR_ID   NUMBER(8),
  CITIZENSHIP        VARCHAR2(2)
);

CREATE SEQUENCE WORKER_ID_SEQ
  MINVALUE 100000
  MAXVALUE 99999999
  START WITH 100000
  INCREMENT BY 1;

ALTER TABLE WORKER
  ADD CONSTRAINT WORKER_PK
      PRIMARY KEY (WORKER_ID);

ALTER TABLE WORKER
  ADD CONSTRAINT WORKER_FK1
      FOREIGN KEY (SUBCONTRACTOR_ID)
      REFERENCES SUBCONTRACTOR (SUBCONTRACTOR_ID);



PROMPT "CREATE TABLE PROJECT_TEAM"

CREATE TABLE PROJECT_TEAM (
  PROJECT_ID    NUMBER(8)     NOT NULL,
  TEAM_ID       NUMBER(8)     NOT NULL,
  CAPTION       VARCHAR2(100) NOT NULL
);

ALTER TABLE PROJECT_TEAM
  ADD CONSTRAINT PROJECT_TEAM_PK
      PRIMARY KEY (PROJECT_ID, TEAM_ID);

ALTER TABLE PROJECT_TEAM
  ADD CONSTRAINT PROJECT_TEAM_FK1
      FOREIGN KEY (PROJECT_ID)
      REFERENCES PROJECT (PROJECT_ID);



PROMPT "CREATE TABLE PROJECT_TEAM_PERSON"

CREATE TABLE PROJECT_TEAM_PERSON (
  PROJECT_ID    NUMBER(8) NOT NULL,
  TEAM_ID       NUMBER(8) NOT NULL,
  WORKER_ID     NUMBER(8) NOT NULL,
  TEAM_LEAD     CHAR(1)   DEFAULT '0' NOT NULL
);

ALTER TABLE PROJECT_TEAM_PERSON
  ADD CONSTRAINT PROJECT_TEAM_PERSON_PK
      PRIMARY KEY (PROJECT_ID, TEAM_ID, WORKER_ID);

ALTER TABLE PROJECT_TEAM_PERSON
  ADD CONSTRAINT PROJECT_TEAM_PERSON_FK1
      FOREIGN KEY (PROJECT_ID, TEAM_ID)
      REFERENCES PROJECT_TEAM (PROJECT_ID, TEAM_ID)
      ON DELETE CASCADE;

ALTER TABLE PROJECT_TEAM_PERSON
  ADD CONSTRAINT PROJECT_TEAM_PERSON_FK2
      FOREIGN KEY (WORKER_ID)
      REFERENCES WORKER (WORKER_ID);



PROMPT "CREATE TABLE PROJECT_WBS"

CREATE TABLE PROJECT_WBS (
  PROJECT_ID           NUMBER(8)    NOT NULL,
  CATEGORY_ID          NUMBER(8)    NOT NULL,
  CBS_CODE             VARCHAR2(20),
  UNIT                 VARCHAR2(3)  NOT NULL,
  METRIC               NUMBER(8,2)  NOT NULL,
  QUANTITY             NUMBER(8,2)  NOT NULL,
  PUP_METRIC           NUMBER(8,2),
  PUP_QUANTITY         NUMBER(8,2),
  PLANNED_METRIC       NUMBER(8,2),
  PLANNED_QUANTITY     NUMBER(8,2),
  CUSTOMER_WBS_CODE    VARCHAR2(20),
  CUSTOMER_WBS_CAPTION VARCHAR2(100)
);

ALTER TABLE PROJECT_WBS
  ADD CONSTRAINT PROJECT_WBS_PK
      PRIMARY KEY (PROJECT_ID, CATEGORY_ID);

ALTER TABLE PROJECT_WBS
  ADD CONSTRAINT PROJECT_WBS_FK1
      FOREIGN KEY (PROJECT_ID)
      REFERENCES PROJECT (PROJECT_ID);

ALTER TABLE PROJECT_WBS
  ADD CONSTRAINT PROJECT_WBS_FK2
      FOREIGN KEY (CATEGORY_ID)
      REFERENCES CATEGORY (CATEGORY_ID);



PROMPT "CREATE TABLE PROJECT_TEAM_TEMPLATE"

CREATE TABLE PROJECT_TEAM_TEMPLATE (
  PROJECT_ID    NUMBER(8)     NOT NULL,
  TEAM_ID       NUMBER(8)     NOT NULL,
  CATEGORY_ID   NUMBER(8)     NOT NULL
);

ALTER TABLE PROJECT_TEAM_TEMPLATE
  ADD CONSTRAINT PROJECT_TEAM_TEMPLATE_PK
      PRIMARY KEY (PROJECT_ID, TEAM_ID, CATEGORY_ID);

ALTER TABLE PROJECT_TEAM_TEMPLATE
  ADD CONSTRAINT PROJECT_TEAM_TEMPLATE_FK1
      FOREIGN KEY (PROJECT_ID, TEAM_ID)
      REFERENCES PROJECT_TEAM (PROJECT_ID, TEAM_ID)
      ON DELETE CASCADE;

ALTER TABLE PROJECT_TEAM_TEMPLATE
  ADD CONSTRAINT PROJECT_TEAM_TEMPLATE_FK2
      FOREIGN KEY (PROJECT_ID, CATEGORY_ID)
      REFERENCES PROJECT_WBS (PROJECT_ID, CATEGORY_ID)
      ON DELETE CASCADE;


PROMPT "CREATE TABLE PROJECT_WBS_MANHOUR"

CREATE TABLE PROJECT_WBS_MANHOUR (
  PROJECT_ID       NUMBER(8)    NOT NULL,
  CATEGORY_ID      NUMBER(8)    NOT NULL,
  TEAM_ID          NUMBER(8)    NOT NULL,
  WORKER_ID        NUMBER(8)    NOT NULL,
  ACTIVITY_DATE    DATE         NOT NULL,
  MANHOUR          NUMBER(4)    DEFAULT 0 NOT NULL,
  OVERTIME         NUMBER(4)    DEFAULT 0,
  LOCAL_MH         NUMBER(4)    DEFAULT 0,
  FOREIGN_MH       NUMBER(4)    DEFAULT 0,
  TR_MH            NUMBER(4)    DEFAULT 0,
  STATUS           VARCHAR2(20) DEFAULT 'INITIAL' NOT NULL
);

ALTER TABLE PROJECT_WBS_MANHOUR
  ADD CONSTRAINT PROJECT_WBS_MANHOUR_PK
      PRIMARY KEY (PROJECT_ID, CATEGORY_ID, TEAM_ID, WORKER_ID, ACTIVITY_DATE);

ALTER TABLE PROJECT_WBS_MANHOUR
  ADD CONSTRAINT PROJECT_WBS_MANHOUR_FK1
      FOREIGN KEY (PROJECT_ID, CATEGORY_ID)
      REFERENCES PROJECT_WBS (PROJECT_ID, CATEGORY_ID);

ALTER TABLE PROJECT_WBS_MANHOUR
  ADD CONSTRAINT PROJECT_WBS_MANHOUR_FK2
      FOREIGN KEY (PROJECT_ID, TEAM_ID, WORKER_ID)
      REFERENCES PROJECT_TEAM_PERSON (PROJECT_ID, TEAM_ID, WORKER_ID);

ALTER TABLE PROJECT_WBS_MANHOUR
  ADD CONSTRAINT PROJECT_WBS_MANHOUR_CK1
      CHECK ( (LOCAL_MH IS NULL AND FOREIGN_MH IS NULL AND TR_MH IS NULL) OR
              (MANHOUR = LOCAL_MH+FOREIGN_MH+TR_MH) );

PROMPT "CREATE TABLE PROJECT_WBS_QUANTITY"

CREATE TABLE PROJECT_WBS_QUANTITY (
  PROJECT_ID        NUMBER(8)    NOT NULL,
  CATEGORY_ID       NUMBER(8)    NOT NULL,
  TEAM_ID           NUMBER(8)    NOT NULL,
  BEGDA             DATE         NOT NULL,
  ENDDA             DATE         NOT NULL,
  QUANTITY          NUMBER(8,2)  NOT NULL,
  STATUS            VARCHAR2(20) DEFAULT 'INITIAL' NOT NULL
);

ALTER TABLE PROJECT_WBS_QUANTITY
  ADD CONSTRAINT PROJECT_WBS_QUANTITY_PK
      PRIMARY KEY (PROJECT_ID, CATEGORY_ID, TEAM_ID, BEGDA);

ALTER TABLE PROJECT_WBS_QUANTITY
  ADD CONSTRAINT PROJECT_WBS_QUANTITY_FK1
      FOREIGN KEY (PROJECT_ID, CATEGORY_ID)
      REFERENCES PROJECT_WBS (PROJECT_ID, CATEGORY_ID);

ALTER TABLE PROJECT_WBS_QUANTITY
  ADD CONSTRAINT PROJECT_WBS_QUANTITY_FK2
      FOREIGN KEY (PROJECT_ID, TEAM_ID)
      REFERENCES PROJECT_TEAM (PROJECT_ID, TEAM_ID);

ALTER TABLE PROJECT_WBS_QUANTITY
  ADD CONSTRAINT PROJECT_WBS_QUANTITY_CK1
      CHECK ( ENDDA > BEGDA );



PROMPT "CREATE TABLE PROJECT_APPROVAL_HISTORY"

CREATE TABLE PROJECT_APPROVAL_HISTORY (
  PROJECT_ID       NUMBER(8)    NOT NULL,
  USERNAME         VARCHAR2(30) NOT NULL,
  APPROVAL         DATE         NOT NULL,
  STATUS           VARCHAR2(20) NOT NULL
);

ALTER TABLE PROJECT_APPROVAL_HISTORY
  ADD CONSTRAINT PROJECT_APPROVAL_HISTORY_PK
      PRIMARY KEY (PROJECT_ID, USERNAME, APPROVAL);

ALTER TABLE PROJECT_APPROVAL_HISTORY
  ADD CONSTRAINT PROJECT_APPROVAL_HISTORY_FK1
      FOREIGN KEY (PROJECT_ID)
      REFERENCES PROJECT (PROJECT_ID);

ALTER TABLE PROJECT_APPROVAL_HISTORY
  ADD CONSTRAINT PROJECT_APPROVAL_HISTORY_FK2
      FOREIGN KEY (USERNAME)
      REFERENCES USER_ACCOUNT (USERNAME);



CREATE OR REPLACE VIEW XXADSA_CBS AS
SELECT FLEX_VALUE AS CBS_CODE, DESCRIPTION AS CAPTION, SUMMARY_FLAG FROM APPS.XXGAMA_ADSA_CBS_V;

CREATE OR REPLACE VIEW XXADSA_EXT_ORGANIZATION AS
SELECT * FROM APPS.XXGAMA_ADSA_ORGANIZATIONS_V;



CREATE OR REPLACE VIEW XXADSA_EXT_SUBCONTRACTOR AS
SELECT LOOKUP_CODE AS EXT_SUBCONTRACTOR, MEANING AS CAPTION FROM APPS.XXGAMA_ADSA_TASERON_LIST_V;



INSERT INTO SUBCONTRACTOR (SUBCONTRACTOR_ID, CAPTION, EXT_SUBCONTRACTOR)
SELECT ROWNUM, MEANING, LOOKUP_CODE FROM APPS.XXGAMA_ADSA_TASERON_LIST_V;



CREATE OR REPLACE VIEW XXADSA_EXT_PERSON AS
SELECT A.PERSON_ID, A.FIRST_NAME, A.LAST_NAME, B.ISOCODE AS NATIONALITY_ID, EMAIL_ADDRESS AS EMAIL, EMPLOYEE_NUMBER,
       BIRINCI_AMIR_PERSON_ID AS SUPERVISOR, ORGANIZATION_ID, ORGANIZATION, KADRO AS POSITION, KATEGORI AS SUBCONTRACTOR
  FROM APPS.XXGAMA_ADSA_PERSONEL_V A, XXADSA_GAMA_COUNTRY_ISOCODES B
 WHERE B.GAMACODE(+)=A.NATIOANALITY_ID;



INSERT INTO WORKER (WORKER_ID,CAPTION,PERSON_ID,SUBCONTRACTOR_ID,CITIZEN_TYPE)
SELECT ROWNUM, A.FIRST_NAME || ' ' || A.LAST_NAME, A.PERSON_ID, B.SUBCONTRACTOR_ID, A.NATIONALITY_ID
  FROM XXADSA_EXT_PERSON A, SUBCONTRACTOR B
 WHERE B.EXT_SUBCONTRACTOR (+) = A.KATEGORI;



CREATE OR REPLACE VIEW PROJECT_WBS_QUANTITY_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, MAX(BEGDA) AS MAX_BEGDA, MAX(ENDDA) AS MAX_ENDDA, SUM(QUANTITY) AS SUM_QUANTITY
  FROM PROJECT_WBS_QUANTITY
 GROUP BY PROJECT_ID, CATEGORY_ID;

CREATE OR REPLACE VIEW PROJECT_WBS_MANHOUR_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, SUM(MANHOUR) AS SUM_MANHOUR
  FROM PROJECT_WBS_MANHOUR
 GROUP BY PROJECT_ID, CATEGORY_ID;



CREATE OR REPLACE VIEW PROJECT_WBS_STATUS AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, P.CAPTION AS PRJ_CAPTION, C.PARENT_ID, C.TREE_CODE, C.CAPTION AS CAT_CAPTION,
       W.UNIT, W.METRIC, W.QUANTITY, W.PUP_METRIC, W.PUP_QUANTITY,
       A.SUM_QUANTITY, B.SUM_MANHOUR
  FROM PROJECT P, CATEGORY C, PROJECT_WBS W, PROJECT_WBS_QUANTITY_AGGR A, PROJECT_WBS_MANHOUR_AGGR B
 WHERE W.PROJECT_ID      = P.PROJECT_ID
   AND W.CATEGORY_ID (+) = C.CATEGORY_ID
   AND A.PROJECT_ID (+)  = W.PROJECT_ID
   AND A.CATEGORY_ID (+) = W.CATEGORY_ID
   AND B.PROJECT_ID (+)  = W.PROJECT_ID
   AND B.CATEGORY_ID (+) = W.CATEGORY_ID;



CREATE OR REPLACE VIEW PROJECT_WBS_TEAM_QTY_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, MAX(BEGDA) AS MAX_BEGDA, MAX(ENDDA) AS MAX_ENDDA, SUM(QUANTITY) AS SUM_QUANTITY
  FROM PROJECT_WBS_QUANTITY
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;

CREATE OR REPLACE VIEW PROJECT_WBS_TEAM_MH_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(MANHOUR) AS SUM_MANHOUR
  FROM PROJECT_WBS_MANHOUR
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;

CREATE OR REPLACE VIEW PROJECT_WBS_TEAM_STATUS AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, T.TEAM_ID, P.CAPTION AS PRJ_CAPTION, C.PARENT_ID, C.TREE_CODE, C.CAPTION AS CAT_CAPTION, T.CAPTION AS TEAM_CAPTION, W.UNIT, W.METRIC, W.QUANTITY,
       A.SUM_QUANTITY, B.SUM_MANHOUR, Q.BEGDA, Q.ENDDA, Q.QUANTITY AS LAST_QUANTITY
  FROM PROJECT P, CATEGORY C, PROJECT_WBS W, PROJECT_TEAM T, PROJECT_WBS_QUANTITY Q, PROJECT_WBS_TEAM_MH_AGGR B, PROJECT_WBS_TEAM_QTY_AGGR A
 WHERE W.PROJECT_ID      = P.PROJECT_ID
   AND W.CATEGORY_ID     = C.CATEGORY_ID
   AND T.PROJECT_ID      = P.PROJECT_ID
   AND B.PROJECT_ID      = W.PROJECT_ID
   AND B.CATEGORY_ID     = W.CATEGORY_ID
   AND B.TEAM_ID         = T.TEAM_ID
   AND A.PROJECT_ID (+)  = B.PROJECT_ID
   AND A.CATEGORY_ID (+) = B.CATEGORY_ID
   AND A.TEAM_ID (+)     = B.TEAM_ID
   AND Q.PROJECT_ID (+)  = A.PROJECT_ID
   AND Q.CATEGORY_ID (+) = A.CATEGORY_ID
   AND Q.TEAM_ID (+)     = A.TEAM_ID
   AND Q.BEGDA (+)       = A.MAX_BEGDA;



CREATE OR REPLACE VIEW PROJECT_PROGRESS AS
SELECT W.PROJECT_ID, W.CATEGORY_ID, W.UNIT,
       W.METRIC, W.QUANTITY, W.METRIC * W.QUANTITY AS WORKFORCE,
       W.PUP_METRIC, W.PUP_QUANTITY, W.PUP_METRIC * W.PUP_QUANTITY AS PUP_WORKFORCE,
       W.PLANNED_METRIC, W.PLANNED_QUANTITY, W.PLANNED_METRIC * W.PLANNED_QUANTITY AS PLANNED_WORKFORCE,
       A.SUM_QUANTITY, B.SUM_MANHOUR
  FROM PROJECT_WBS W, PROJECT_WBS_QUANTITY_AGGR A, PROJECT_WBS_MANHOUR_AGGR B
 WHERE A.PROJECT_ID (+)  = W.PROJECT_ID
   AND A.CATEGORY_ID (+) = W.CATEGORY_ID
   AND B.PROJECT_ID (+)  = W.PROJECT_ID
   AND B.CATEGORY_ID (+) = W.CATEGORY_ID;


CREATE OR REPLACE VIEW PROJECT_WBS_QTY_APPROVED AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(QUANTITY) AS APPROVED_QUANTITY
  FROM PROJECT_WBS_QUANTITY
 WHERE STATUS = 'APPROVE_QUANTITY'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;



CREATE OR REPLACE VIEW PROJECT_WBS_QTY_INITIAL AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(QUANTITY) AS QUANTITY_TO_APPROVE
  FROM PROJECT_WBS_QUANTITY
 WHERE STATUS = 'INITIAL'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;



CREATE OR REPLACE VIEW PROJECT_WBS_QTY_TO_APR AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, T.TEAM_ID, P.CAPTION AS PROJECT_CAPTION, C.TREE_CODE, C.CAPTION AS CATEGORY_CAPTION, T.CAPTION AS TEAM_CAPTION, W.UNIT, W.METRIC, W.QUANTITY,
       A.APPROVED_QUANTITY, B.BEGDA, B.ENDDA, B.QUANTITY AS QUANTITY_TO_APPROVE
  FROM PROJECT P, CATEGORY C, PROJECT_TEAM T, PROJECT_WBS W, PROJECT_WBS_QTY_APPROVED A, PROJECT_WBS_QUANTITY B --PROJECT_WBS_QTY_INITIAL B
 WHERE W.PROJECT_ID      = P.PROJECT_ID
   AND W.CATEGORY_ID     = C.CATEGORY_ID
   AND T.PROJECT_ID      = P.PROJECT_ID
   AND B.PROJECT_ID      = W.PROJECT_ID
   AND B.CATEGORY_ID     = W.CATEGORY_ID
   AND B.TEAM_ID         = T.TEAM_ID
   AND B.STATUS          = 'INITIAL'
   AND A.PROJECT_ID (+)  = B.PROJECT_ID
   AND A.CATEGORY_ID (+) = B.CATEGORY_ID
   AND A.TEAM_ID (+)     = B.TEAM_ID;



CREATE OR REPLACE VIEW PROJECT_WBS_MH_APPROVED AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(MANHOUR) AS APPROVED_MANHOUR
  FROM PROJECT_WBS_MANHOUR
 WHERE STATUS = 'APPROVE_MANHOUR'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;



CREATE OR REPLACE VIEW PROJECT_WBS_MH_INITIAL AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, ACTIVITY_DATE, SUM(MANHOUR) AS MANHOUR_TO_APPROVE
  FROM PROJECT_WBS_MANHOUR
 WHERE STATUS = 'INITIAL'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID, ACTIVITY_DATE;



CREATE OR REPLACE VIEW PROJECT_WBS_MH_TO_APR AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, T.TEAM_ID, P.CAPTION AS PROJECT_CAPTION, C.TREE_CODE, C.CAPTION AS CATEGORY_CAPTION, T.CAPTION AS TEAM_CAPTION,
       W.UNIT, W.METRIC, W.QUANTITY, A.APPROVED_MANHOUR, B.ACTIVITY_DATE, B.MANHOUR_TO_APPROVE
  FROM PROJECT P, CATEGORY C, PROJECT_TEAM T, PROJECT_WBS W, PROJECT_WBS_MH_APPROVED A, PROJECT_WBS_MH_INITIAL B
 WHERE W.PROJECT_ID      = P.PROJECT_ID
   AND W.CATEGORY_ID     = C.CATEGORY_ID
   AND T.PROJECT_ID      = P.PROJECT_ID
   AND B.PROJECT_ID      = W.PROJECT_ID
   AND B.CATEGORY_ID     = W.CATEGORY_ID
   AND B.TEAM_ID         = T.TEAM_ID
   AND A.PROJECT_ID (+)  = B.PROJECT_ID
   AND A.CATEGORY_ID (+) = B.CATEGORY_ID
   AND A.TEAM_ID (+)     = B.TEAM_ID;



CREATE OR REPLACE VIEW PROJECT_SUBCONTRACTOR_SUMMARY AS
SELECT T.PROJECT_ID, O.SUBCONTRACTOR_ID, C.CATEGORY_ID, C.TREE_CODE, C.CAPTION, W.CUSTOMER_WBS_CODE, W.CUSTOMER_WBS_CAPTION, W.CBS_CODE, W.UNIT, W.METRIC, W.QUANTITY, W.PUP_METRIC, W.PUP_QUANTITY, W.PLANNED_METRIC, W.PLANNED_QUANTITY,
       SUM(Q.APPROVED_QUANTITY) AS SUM_QUANTITY, SUM(M.APPROVED_MANHOUR) AS SUM_MANHOUR
  FROM CATEGORY C, PROJECT_WBS W, PROJECT_TEAM T, PROJECT_WBS_MH_APPROVED M, PROJECT_WBS_QTY_APPROVED Q, PROJECT_TEAM_PERSON P, WORKER O
 WHERE W.PROJECT_ID      = T.PROJECT_ID 
   AND W.CATEGORY_ID     = C.CATEGORY_ID 
   AND Q.PROJECT_ID      = W.PROJECT_ID 
   AND Q.CATEGORY_ID     = W.CATEGORY_ID 
   AND Q.TEAM_ID         = T.TEAM_ID
   AND M.PROJECT_ID      = W.PROJECT_ID 
   AND M.CATEGORY_ID     = W.CATEGORY_ID 
   AND M.TEAM_ID         = T.TEAM_ID
   AND P.PROJECT_ID      = T.PROJECT_ID
   AND P.TEAM_ID         = T.TEAM_ID
   AND P.TEAM_LEAD       = 1
   AND O.WORKER_ID       = P.WORKER_ID
 GROUP BY T.PROJECT_ID, O.SUBCONTRACTOR_ID, C.CATEGORY_ID, C.TREE_CODE, C.CAPTION, W.CUSTOMER_WBS_CODE, W.CUSTOMER_WBS_CAPTION, W.CBS_CODE, W.UNIT, W.METRIC, W.QUANTITY, W.PUP_METRIC, W.PUP_QUANTITY, W.PLANNED_METRIC, W.PLANNED_QUANTITY;



INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('COUNTRY',          'PROJECT',              'COUNTRY');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('DURATION_TYPE',    'PROJECT',              'DURATION_TYPE');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('MEASUREMENT_UNIT', 'PROJECT_WBS',          'UNIT');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('MEASUREMENT_UNIT', 'CATEGORY',             'UNIT');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('MEASUREMENT_UNIT', 'CATEGORY',             'UNIT2');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('YESNO',            'CATEGORY',             'MAIN_FLAG');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('YESNO',            'PROJECT_TEAM_PERSON',  'TEAM_LEAD');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('YESNO',            'PROJECT_WBS_QUANTITY', 'IS_SUBCONTRACTOR');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('COUNTRY' ,         'WORKER',               'CITIZENSHIP');

INSERT INTO MAIN_MENU (MENU, CAPTION, ICON, DISPLAY_ORDER) VALUES ('MANHOUR',  'MANHOUR', 'fa-circle', 1000);

INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('CATEGORY',         'CATEGORY',         'fa-sitemap',          'category/list',              'MANHOUR', 10);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('PROJECT',          'PROJECT',          'fa-building',         'project/list',               'MANHOUR', 20);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('SUBCONTRACTOR',    'SUBCONTRACTOR',    'fa-male',             'subcontractor/list',         'MANHOUR', 30);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('PROJECT_MANHOUR',  'PROJECT_MANHOUR',  'fa-calendar-plus-o',  'projectWbsManhour/select',   'MANHOUR', 40);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('PROJECT_QUANTITY', 'PROJECT_QUANTITY', 'fa-industry',         'projectWbsQuantity/select',  'MANHOUR', 50);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('APPROVE_PROGRESS', 'APPROVE_PROGRESS', 'fa-calendar-check-o', 'project/approveProgress',    'MANHOUR', 60);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('APPROVE_MANHOUR',  'APPROVE_MANHOUR',  'fa-calendar-check-o', 'projectWbsManhour/approve',  'MANHOUR', 61);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('APPROVE_QUANTITY', 'APPROVE_QUANTITY', 'fa-fort-awesome',     'projectWbsQuantity/approve', 'MANHOUR', 70);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('REPORT',           'REPORT',           'fa-file-text',        'report/list',                'MANHOUR', 80);

INSERT INTO AUTHORITY_OBJECT (OBJECT_TYPE) VALUES ('PROJECT');
INSERT INTO AUTHORITY_OBJECT (OBJECT_TYPE) VALUES ('PROJECT_TEAM');
INSERT INTO AUTHORITY_OBJECT (OBJECT_TYPE) VALUES ('PROJECT_WBS');
INSERT INTO AUTHORITY_OBJECT (OBJECT_TYPE) VALUES ('PROJECT_TEAM_PERSON');

INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT',             'APPROVE_FINAL');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT',             'APPROVE_WBS');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT',             'APPROVE_WITHDRAW');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT',             'APPROVE_PROGRESS');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT_TEAM',        'APPROVE_MANHOUR');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT_TEAM',        'APPROVE_QUANTITY');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT_TEAM_PERSON', 'ADD_EMPLOYEE');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT_TEAM_PERSON', 'ADD_SUBCONTRACTOR');

INSERT INTO AUTHORITY_GROUP (AUTHORITY_GROUP,CAPTION) VALUES ('MANHOURADMIN', 'MANHOURADMIN');

INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT',             'APPROVE_FINAL',     '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT',             'APPROVE_WBS',       '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT',             'APPROVE_WITHDRAW',  '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT',             'APPROVE_PROGRESS',  '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT_TEAM',        'APPROVE_MANHOUR',   '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT_TEAM',        'APPROVE_QUANTITY',  '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT_TEAM_PERSON', 'ADD_EMPLOYEE',      '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT_TEAM_PERSON', 'ADD_SUBCONTRACTOR', '*');

INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'PROJECT');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'CATEGORY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'SUBCONTRACTOR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'PROJECT_MANHOUR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'PROJECT_QUANTITY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'APPROVE_PROGRESS');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'REPORT');

INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'SELECT', 'PROJECT' );
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'SELECT', 'CATEGORY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'SELECT', 'SUBCONTRACTOR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'SELECT', 'WORKER');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'SELECT', 'PROJECT_TEAM');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'SELECT', 'PROJECT_TEAM_PERSON');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'SELECT', 'PROJECT_WBS');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'SELECT', 'PROJECT_WBS_MANHOUR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'SELECT', 'PROJECT_WBS_QUANTITY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'SELECT', 'PROJECT_APPROVAL_HISTORY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'SELECT', 'PROJECT_TEAM_TEMPLATE');

INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'INSERT', 'PROJECT' );
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'INSERT', 'CATEGORY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'INSERT', 'SUBCONTRACTOR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'INSERT', 'WORKER');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'INSERT', 'PROJECT_TEAM');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'INSERT', 'PROJECT_TEAM_PERSON');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'INSERT', 'PROJECT_WBS');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'INSERT', 'PROJECT_WBS_MANHOUR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'INSERT', 'PROJECT_WBS_QUANTITY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'INSERT', 'PROJECT_APPROVAL_HISTORY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'INSERT', 'PROJECT_TEAM_TEMPLATE');

INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'UPDATE', 'PROJECT' );
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'UPDATE', 'CATEGORY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'UPDATE', 'SUBCONTRACTOR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'UPDATE', 'WORKER');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'UPDATE', 'PROJECT_TEAM');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'UPDATE', 'PROJECT_TEAM_PERSON');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'UPDATE', 'PROJECT_WBS');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'UPDATE', 'PROJECT_WBS_MANHOUR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'UPDATE', 'PROJECT_WBS_QUANTITY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'UPDATE', 'PROJECT_APPROVAL_HISTORY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'UPDATE', 'PROJECT_TEAM_TEMPLATE');

INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'DELETE', 'PROJECT' );
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'DELETE', 'CATEGORY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'DELETE', 'SUBCONTRACTOR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'DELETE', 'WORKER');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'DELETE', 'PROJECT_TEAM');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'DELETE', 'PROJECT_TEAM_PERSON');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'DELETE', 'PROJECT_WBS');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'DELETE', 'PROJECT_WBS_MANHOUR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'DELETE', 'PROJECT_WBS_QUANTITY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'DELETE', 'PROJECT_APPROVAL_HISTORY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'TABLE', 'DELETE', 'PROJECT_TEAM_TEMPLATE');

INSERT INTO USER_ACCOUNT (USERNAME,PASSWORD,CAPTION,STATUS) VALUES ('MHADMIN', '1', 'MANHOUR ADMIN', 'A');

INSERT INTO USER_AUTHORIZATION (USERNAME,AUTHORITY_GROUP) VALUES ('MHADMIN', 'MANHOURADMIN');
INSERT INTO USER_AUTHORIZATION (USERNAME,AUTHORITY_GROUP) VALUES ('MUSTAFA', 'MANHOURADMIN');

INSERT INTO LANGUAGE (LANGCODE, CAPTION, DIRECTION, FLAG) VALUES ('RU', 'RUSSIAN', 'LEFT',  'flag-icon-ru');

commit;

spool off
