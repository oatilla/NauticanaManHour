CREATE TABLE PROJECT (
  PROJECT_ID          INTEGER      NOT NULL,
  CAPTION             VARCHAR(100) NOT NULL,
  STATUS              VARCHAR(20)  DEFAULT 'INITIAL' NOT NULL,
  COUNTRY             CHAR(2)      NOT NULL,
  LOCATION            VARCHAR(50),
  CUSTOMER            VARCHAR(100), 
  CONTRACT_DATE       TIMESTAMP,
  AREA_HANDOVER       DATE,
  DURATION            INTEGER,
  REVIZED_DURATION    INTEGER,
  REVIZED_COMPLETION  TIMESTAMP,
  EXPECTED_COMPLETION TIMESTAMP,
  END_OF_WARRANTY     TIMESTAMP,
  CONTRACTED_AMOUNT   INTEGER,
  CONTRACT_EXCHANGE   CHAR(3),
  EXPECTED_COST       INTEGER,
  ADVANCE_PERCENT     TINYINT,
  LETTER_OF_ADVANCE   TINYINT,
  LETTER_OF_WARRANTY  TINYINT,
  ORGANIZATION_ID     INTEGER
);


ALTER TABLE PROJECT
  ADD CONSTRAINT PROJECT_PK
      PRIMARY KEY (PROJECT_ID);

CREATE SEQUENCE PROJECT_ID_SEQ
  START WITH 1
  INCREMENT BY 1;


CREATE TABLE CATEGORY (
  CATEGORY_ID       INTEGER       NOT NULL,
  PARENT_ID         INTEGER,
  CAPTION           VARCHAR(100)  NOT NULL,
  CAT_INDEX         VARCHAR(2)    NOT NULL,
  DETAILS           VARCHAR(200),
  UNIT              VARCHAR(20),
  CAT_LEVEL         TINYINT       NOT NULL,
  TREE_CODE         VARCHAR(20)   NOT NULL,
  CBS_CODE          VARCHAR(20),
  PROJECT_ID        INTEGER,
  MAIN_FLAG         CHAR(1)       NOT NULL
);

ALTER TABLE CATEGORY
  ADD CONSTRAINT CATEGORY_PK
      PRIMARY KEY (CATEGORY_ID);

CREATE SEQUENCE CATEGORY_ID_SEQ
  START WITH 10000
  INCREMENT BY 1;


CREATE TABLE SUBCONTRACTOR (
  SUBCONTRACTOR_ID     INTEGER      NOT NULL,
  CAPTION              VARCHAR(100) NOT NULL,
  EXT_SUBCONTRACTOR    VARCHAR(50)
);

ALTER TABLE SUBCONTRACTOR
  ADD CONSTRAINT SUBCONTRACTOR_PK
      PRIMARY KEY (SUBCONTRACTOR_ID);

CREATE SEQUENCE SUBCONTRACTOR_ID_SEQ
  START WITH 1000000
  INCREMENT BY 1;



CREATE TABLE WORKER (
  WORKER_ID          INTEGER      NOT NULL,
  CAPTION            VARCHAR(100) NOT NULL,
  PERSON_ID          INTEGER,
  SUBCONTRACTOR_ID   INTEGER,  
  CITIZENSHIP        CHAR(2)
);

CREATE SEQUENCE WORKER_ID_SEQ
  START WITH 5000000
  INCREMENT BY 1;

ALTER TABLE WORKER
  ADD CONSTRAINT WORKER_PK
      PRIMARY KEY (WORKER_ID);

ALTER TABLE WORKER
  ADD CONSTRAINT WORKER_FK1
      FOREIGN KEY (SUBCONTRACTOR_ID)
      REFERENCES SUBCONTRACTOR (SUBCONTRACTOR_ID);



CREATE TABLE PROJECT_TEAM (
  PROJECT_ID    INTEGER       NOT NULL,
  TEAM_ID       INTEGER       NOT NULL,
  CAPTION       VARCHAR(100)  NOT NULL
);

ALTER TABLE PROJECT_TEAM
  ADD CONSTRAINT PROJECT_TEAM_PK
      PRIMARY KEY (PROJECT_ID, TEAM_ID);

ALTER TABLE PROJECT_TEAM
  ADD CONSTRAINT PROJECT_TEAM_FK1
      FOREIGN KEY (PROJECT_ID)
      REFERENCES PROJECT (PROJECT_ID);



CREATE TABLE PROJECT_TEAM_PERSON (
  PROJECT_ID    INTEGER   NOT NULL,
  TEAM_ID       INTEGER   NOT NULL,
  WORKER_ID     INTEGER   NOT NULL,
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



CREATE TABLE PROJECT_WBS (
  PROJECT_ID           INTEGER      NOT NULL,
  CATEGORY_ID          INTEGER      NOT NULL,
  CBS_CODE             VARCHAR(20),
  UNIT                 CHAR(3)      NOT NULL,
  METRIC               DECIMAL(8,2) NOT NULL,
  QUANTITY             DECIMAL(8,2) NOT NULL,
  PUP_METRIC           DECIMAL(8,2),
  PUP_QUANTITY         DECIMAL(8,2),
  PLANNED_METRIC       DECIMAL(8,2),
  PLANNED_QUANTITY     DECIMAL(8,2),
  CUSTOMER_WBS_CODE    VARCHAR(20),
  CUSTOMER_WBS_CAPTION VARCHAR(100)
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



CREATE TABLE PROJECT_TEAM_TEMPLATE (
  PROJECT_ID    INTEGER  NOT NULL,
  TEAM_ID       INTEGER  NOT NULL,
  CATEGORY_ID   INTEGER  NOT NULL
);

ALTER TABLE PROJECT_TEAM_TEMPLATE
  ADD CONSTRAINT PROJECT_TEAM_TEMPLATE_PK
      PRIMARY KEY (PROJECT_ID, TEAM_ID, CATEGORY_ID);

ALTER TABLE PROJECT_TEAM_TEMPLATE
  ADD CONSTRAINT PROJECT_TEAM_TEMPLATE_FK1
      FOREIGN KEY (PROJECT_ID, TEAM_ID)
      REFERENCES PROJECT_TEAM (PROJECT_ID, TEAM_ID);

ALTER TABLE PROJECT_TEAM_TEMPLATE
  ADD CONSTRAINT PROJECT_TEAM_TEMPLATE_FK2
      FOREIGN KEY (PROJECT_ID, CATEGORY_ID)
      REFERENCES PROJECT_WBS (PROJECT_ID, CATEGORY_ID);



CREATE TABLE PROJECT_WBS_MANHOUR (
  PROJECT_ID       INTEGER      NOT NULL,
  CATEGORY_ID      INTEGER      NOT NULL,
  TEAM_ID          INTEGER      NOT NULL,
  WORKER_ID        INTEGER      NOT NULL,
  ACTIVITY_DATE    TIMESTAMP    NOT NULL,
  MANHOUR          SMALLINT     NOT NULL,
  OVERTIME         SMALLINT,
  LOCAL_MH         SMALLINT,
  FOREIGN_MH       SMALLINT,
  TR_MH            SMALLINT,
  STATUS           VARCHAR(20) DEFAULT 'INITIAL' NOT NULL
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



CREATE TABLE PROJECT_WBS_QUANTITY (
  PROJECT_ID        INTEGER      NOT NULL,
  CATEGORY_ID       INTEGER      NOT NULL,
  TEAM_ID           INTEGER      NOT NULL,
  BEGDA             TIMESTAMP    NOT NULL,
  ENDDA             TIMESTAMP    NOT NULL,
  QUANTITY          DECIMAL(8,2) NOT NULL,
  STATUS            VARCHAR(20)  DEFAULT 'INITIAL' NOT NULL
);

ALTER TABLE PROJECT_WBS_QUANTITY
  ADD CONSTRAINT PROJECT_WBS_QUANTITY_PK
      PRIMARY KEY (PROJECT_ID, CATEGORY_ID, BEGDA);

ALTER TABLE PROJECT_WBS_QUANTITY
  ADD CONSTRAINT PROJECT_WBS_QUANTITY_FK
      FOREIGN KEY (PROJECT_ID, CATEGORY_ID)
      REFERENCES PROJECT_WBS (PROJECT_ID, CATEGORY_ID);

ALTER TABLE PROJECT_WBS_QUANTITY
  ADD CONSTRAINT PROJECT_WBS_QUANTITY_FK2
      FOREIGN KEY (PROJECT_ID, TEAM_ID)
      REFERENCES PROJECT_TEAM (PROJECT_ID, TEAM_ID);

ALTER TABLE PROJECT_WBS_QUANTITY
  ADD CONSTRAINT PROJECT_WBS_QUANTITY_CK1
      CHECK ( ENDDA > BEGDA );



CREATE TABLE PROJECT_APPROVAL_HISTORY (
  PROJECT_ID       INTEGER     NOT NULL,
  USERNAME         VARCHAR(30) NOT NULL,
  APPROVAL         TIMESTAMP   NOT NULL,
  STATUS           VARCHAR(20) NOT NULL
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



CREATE TABLE XXADSA_CBS (
  CBS_CODE      VARCHAR(20)  NOT NULL,
  CAPTION       VARCHAR(100) NOT NULL, 
  SUMMARY_FLAG  CHAR(1)      NOT NULL
);

ALTER TABLE XXADSA_CBS
  ADD CONSTRAINT XXADSA_CBS_PK
      PRIMARY KEY (CBS_CODE);



CREATE TABLE XXADSA_EXT_ORGANIZATION (
  ORGANIZATION_ID    INTEGER,
  ORGANIZATION_NAME  VARCHAR(40),
  COMPANY            VARCHAR(40),
  LOCATION_ID        INTEGER,
  LOCATION_CODE      VARCHAR(40)
);

ALTER TABLE XXADSA_EXT_ORGANIZATION
  ADD CONSTRAINT XXADSA_EXT_ORGANIZATION_PK
      PRIMARY KEY (ORGANIZATION_ID);



CREATE TABLE XXADSA_EXT_SUBCONTRACTOR  (
  EXT_SUBCONTRACTOR   VARCHAR(40),
  CAPTION             VARCHAR(100)
);

ALTER TABLE XXADSA_EXT_SUBCONTRACTOR
  ADD CONSTRAINT XXADSA_EXT_SUBCONTRACTOR_PK
      PRIMARY KEY (EXT_SUBCONTRACTOR);



CREATE TABLE XXADSA_EXT_PERSON (
  PERSON_ID          INTEGER,
  FIRST_NAME         VARCHAR(40),
  LAST_NAME          VARCHAR(40),
  NATIONALITY_ID     VARCHAR(2),
  EMAIL              VARCHAR(80),
  EMPLOYEE_NUMBER    VARCHAR(20),
  SUPERVISOR         INTEGER,
  ORGANIZATION_ID    INTEGER,
  ORGANIZATION       VARCHAR(80),
  POSITION           VARCHAR(30),
  SUBCONTRACTOR      VARCHAR(30)
);

ALTER TABLE XXADSA_EXT_PERSON
  ADD CONSTRAINT XXADSA_EXT_PERSON_PK
      PRIMARY KEY (PERSON_ID);



CREATE VIEW PROJECT_WBS_QUANTITY_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, MAX(BEGDA) AS MAX_BEGDA, MAX(ENDDA) AS MAX_ENDDA, SUM(QUANTITY) AS SUM_QUANTITY
  FROM PROJECT_WBS_QUANTITY
 GROUP BY PROJECT_ID, CATEGORY_ID;



CREATE VIEW PROJECT_WBS_MANHOUR_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, SUM(MANHOUR) AS SUM_MANHOUR
  FROM PROJECT_WBS_MANHOUR
 GROUP BY PROJECT_ID, CATEGORY_ID;



CREATE VIEW PROJECT_WBS_STATUS AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, P.CAPTION AS PRJ_CAPTION, C.TREE_CODE, C.CAPTION AS CAT_CAPTION, W.UNIT,
       W.METRIC, W.QUANTITY, W.PUP_METRIC, W.PUP_QUANTITY, Q.SUM_QUANTITY, M.SUM_MANHOUR
  FROM ( ( PROJECT AS P
             INNER JOIN (CATEGORY AS C
                            INNER JOIN PROJECT_WBS AS W
                               ON C.CATEGORY_ID = W.CATEGORY_ID)
                ON P.PROJECT_ID = W.PROJECT_ID)
  LEFT JOIN PROJECT_WBS_MANHOUR_AGGR AS M
    ON W.CATEGORY_ID = M.CATEGORY_ID
   AND W.PROJECT_ID = M.PROJECT_ID )
  LEFT JOIN PROJECT_WBS_QUANTITY_AGGR AS Q
    ON W.CATEGORY_ID = Q.CATEGORY_ID
   AND W.PROJECT_ID = Q.PROJECT_ID;



CREATE VIEW PROJECT_WBS_TEAM_QTY_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, MAX(BEGDA) AS MAX_BEGDA, MAX(ENDDA) AS MAX_ENDDA, SUM(QUANTITY) AS SUM_QUANTITY
  FROM PROJECT_WBS_QUANTITY
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;

CREATE VIEW PROJECT_WBS_TEAM_MH_AGGR AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(MANHOUR) AS SUM_MANHOUR
  FROM PROJECT_WBS_MANHOUR
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;

CREATE VIEW PROJECT_WBS_TEAM_STATUS AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, T.TEAM_ID, P.CAPTION AS PRJ_CAPTION, C.TREE_CODE, C.CAPTION AS CAT_CAPTION, T.CAPTION AS TEAM_CAPTION,
       W.UNIT, W.METRIC, W.QUANTITY, A.SUM_QUANTITY, B.SUM_MANHOUR, Q.BEGDA, Q.ENDDA, Q.QUANTITY AS LAST_QUANTITY
  FROM ( (CATEGORY AS C
             INNER JOIN (PROJECT AS P
                           INNER JOIN (PROJECT_WBS AS W
                                             INNER JOIN (PROJECT_TEAM AS T
                                                                 INNER JOIN PROJECT_WBS_TEAM_MH_AGGR AS B
                                                                    ON T.TEAM_ID = B.TEAM_ID)
                                                ON W.PROJECT_ID  = B.PROJECT_ID
                                               AND W.CATEGORY_ID = B.CATEGORY_ID)
                              ON P.PROJECT_ID = W.PROJECT_ID
                             AND P.PROJECT_ID = T.PROJECT_ID)
                ON C.CATEGORY_ID = W.CATEGORY_ID)
              LEFT JOIN PROJECT_WBS_TEAM_QTY_AGGR AS A
                ON B.TEAM_ID     = A.TEAM_ID
               AND B.CATEGORY_ID = A.CATEGORY_ID
               AND B.PROJECT_ID  = A.PROJECT_ID)
              LEFT JOIN PROJECT_WBS_QUANTITY AS Q
                ON Q.BEGDA       = A.MAX_BEGDA
               AND Q.TEAM_ID     = A.TEAM_ID
               AND Q.CATEGORY_ID = A.CATEGORY_ID
               AND Q.PROJECT_ID  = A.PROJECT_ID;



CREATE VIEW PROJECT_WBS_QTY_APPROVED AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(QUANTITY) AS APPROVED_QUANTITY
  FROM PROJECT_WBS_QUANTITY
 WHERE STATUS = 'APPROVE_QUANTITY'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;



CREATE VIEW PROJECT_WBS_QTY_INITIAL AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(QUANTITY) AS QUANTITY_TO_APPROVE
  FROM PROJECT_WBS_QUANTITY
 WHERE STATUS = 'INITIAL'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;



CREATE VIEW PROJECT_WBS_QTY_TO_APR AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, T.TEAM_ID, P.CAPTION AS PROJECT_CAPTION, C.TREE_CODE, C.CAPTION AS CATEGORY_CAPTION, T.CAPTION AS TEAM_CAPTION, W.UNIT, W.METRIC, W.QUANTITY, A.APPROVED_QUANTITY, B.BEGDA, B.ENDDA, B.QUANTITY AS B.QUANTITY_TO_APPROVE
  FROM ( ( (CATEGORY AS C
               INNER JOIN (PROJECT AS P
                             INNER JOIN PROJECT_WBS AS W
                                ON P.PROJECT_ID = W.PROJECT_ID)
                  ON C.CATEGORY_ID = W.CATEGORY_ID)
               INNER JOIN PROJECT_TEAM AS T
                  ON P.PROJECT_ID = T.PROJECT_ID)
               INNER JOIN PROJECT_WBS_QUANTITY AS B
                  ON B.TEAM_ID     = T.TEAM_ID
                 AND B.CATEGORY_ID = W.CATEGORY_ID
                 AND B.PROJECT_ID  = W.PROJECT_ID
                 AND B.STATUS      = 'INITIAL')
                LEFT JOIN PROJECT_WBS_QTY_APPROVED AS A
                  ON B.TEAM_ID     = A.TEAM_ID
                 AND B.CATEGORY_ID = A.CATEGORY_ID
                 AND B.PROJECT_ID  = A.PROJECT_ID;



CREATE VIEW PROJECT_WBS_MH_APPROVED AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, SUM(MANHOUR) AS APPROVED_MANHOUR
  FROM PROJECT_WBS_MANHOUR
 WHERE STATUS = 'APPROVE_MANHOUR'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID;



CREATE VIEW PROJECT_WBS_MH_INITIAL AS
SELECT PROJECT_ID, CATEGORY_ID, TEAM_ID, ACTIVITY_DATE, SUM(MANHOUR) AS MANHOUR_TO_APPROVE
  FROM PROJECT_WBS_MANHOUR
 WHERE STATUS = 'INITIAL'
 GROUP BY PROJECT_ID, CATEGORY_ID, TEAM_ID, ACTIVITY_DATE;



CREATE VIEW PROJECT_WBS_MH_TO_APR AS
SELECT P.PROJECT_ID, C.CATEGORY_ID, T.TEAM_ID, P.CAPTION AS PROJECT_CAPTION, C.TREE_CODE, C.CAPTION AS CATEGORY_CAPTION, T.CAPTION AS TEAM_CAPTION, W.UNIT, W.METRIC, W.QUANTITY, A.APPROVED_MANHOUR, B.ACTIVITY_DATE, B.MANHOUR_TO_APPROVE
  FROM ( ( (CATEGORY AS C
               INNER JOIN (PROJECT AS P
                             INNER JOIN PROJECT_WBS AS W
                                ON P.PROJECT_ID = W.PROJECT_ID)
                  ON C.CATEGORY_ID = W.CATEGORY_ID)
               INNER JOIN PROJECT_TEAM AS T
                  ON P.PROJECT_ID = T.PROJECT_ID)
               INNER JOIN PROJECT_WBS_MH_INITIAL AS B
                  ON T.TEAM_ID     = B.TEAM_ID
                 AND W.CATEGORY_ID = B.CATEGORY_ID
                 AND W.PROJECT_ID  = B.PROJECT_ID)
                LEFT JOIN PROJECT_WBS_MH_APPROVED AS A
                  ON B.TEAM_ID     = A.TEAM_ID
                 AND B.CATEGORY_ID = A.CATEGORY_ID
                 AND B.PROJECT_ID  = A.PROJECT_ID;



CREATE VIEW PROJECT_SUBCONTRACTOR_SUMMARY AS
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
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('EXCHANGE',         'PROJECT',              'CONTRACT_EXCHANGE');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('MEASUREMENT_UNIT', 'PROJECT_WBS',          'UNIT');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('YESNO',            'CATEGORY',             'MAIN_FLAG');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('YESNO',            'PROJECT_TEAM_PERSON',  'TEAM_LEAD');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('YESNO',            'PROJECT_WBS_QUANTITY', 'IS_SUBCONTRACTOR');
INSERT INTO DOMAIN_LOOKUP (DOMAIN, TABLENAME, FIELDNAME) VALUES ('CITIZEN_TYPE',     'WORKER',               'CITIZEN_TYPE');

INSERT INTO MAIN_MENU (MENU, CAPTION, ICON, DISPLAY_ORDER) VALUES ('MANHOUR',  'MANHOUR', 'fa-circle', 1000);

INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('CATEGORY',         'CATEGORY',         'fa-sitemap',          'category/list',              'MANHOUR', 10);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('PROJECT',          'PROJECT',          'fa-building',         'project/list',               'MANHOUR', 20);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('SUBCONTRACTOR',    'SUBCONTRACTOR',    'fa-male',             'subcontractor/list',         'MANHOUR', 30);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('PROJECT_MANHOUR',  'PROJECT_MANHOUR',  'fa-calendar-plus-o',  'projectWbsManhour/select',   'MANHOUR', 40);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('PROJECT_QUANTITY', 'PROJECT_QUANTITY', 'fa-industry',         'projectWbsQuantity/select',  'MANHOUR', 50);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('APPROVE_MANHOUR',  'APPROVE_MANHOUR',  'fa-calendar-check-o', 'projectWbsManhour/approve',  'MANHOUR', 60);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('APPROVE_QUANTITY', 'APPROVE_QUANTITY', 'fa-fort-awesome',     'projectWbsQuantity/approve', 'MANHOUR', 70);
INSERT INTO SCREEN_PAGE (PAGENAME,CAPTION,ICON,URL,MENU,DISPLAY_ORDER) VALUES ('REPORT',           'REPORT',           'fa-file-text',        'report/list',                'MANHOUR', 80);

INSERT INTO AUTHORITY_OBJECT (OBJECT_TYPE) VALUES ('PROJECT');
INSERT INTO AUTHORITY_OBJECT (OBJECT_TYPE) VALUES ('PROJECT_TEAM');
INSERT INTO AUTHORITY_OBJECT (OBJECT_TYPE) VALUES ('PROJECT_WBS');
INSERT INTO AUTHORITY_OBJECT (OBJECT_TYPE) VALUES ('PROJECT_TEAM_PERSON');

INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT',             'APPROVE_FINAL');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT',             'APPROVE_WBS');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT',             'APPROVE_WITHDRAW');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT_TEAM',        'APPROVE_MANHOUR');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT_TEAM',        'APPROVE_QUANTITY');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT_TEAM_PERSON', 'ADD_EMPLOYEE');
INSERT INTO AUTHORITY_OBJECT_ACTION (OBJECT_TYPE,ACTION) VALUES ('PROJECT_TEAM_PERSON', 'ADD_SUBCONTRACTOR');

INSERT INTO AUTHORITY_GROUP (AUTHORITY_GROUP,CAPTION) VALUES ('MANHOURADMIN', 'MANHOURADMIN');

INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT',             'APPROVE_FINAL',     '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT',             'APPROVE_WBS',       '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT',             'APPROVE_WITHDRAW',  '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT_TEAM',        'APPROVE_MANHOUR',   '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT_TEAM',        'APPROVE_QUANTITY',  '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT_TEAM_PERSON', 'ADD_EMPLOYEE',      '*');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PROJECT_TEAM_PERSON', 'ADD_SUBCONTRACTOR', '*');

INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'PROJECT');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'CATEGORY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'SUBCONTRACTOR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'PROJECT_MANHOUR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'PROJECT_QUANTITY');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'APPROVE_MANHOUR');
INSERT INTO OBJECT_AUTHORIZATION (AUTHORITY_GROUP,OBJECT_TYPE,ACTION,KEY_VALUE) VALUES ('MANHOURADMIN', 'PAGE', 'ACCESS', 'APPROVE_QUANTITY');
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
