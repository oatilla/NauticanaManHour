<div class="box box-primary">
	<div class="box-header with-border">
		<h3 class="box-title"> ${PAGETITLE} </h3>
	</div>

	<form:form class="form-horizontal" method="post" modelAttribute="record" id="f">
	<input type="hidden" name="nextpage" value="language/show?id=${record.id.langcode}"/>

	<div class="box-body">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="id"> ${ TTT } </label>
			<div class="col-sm-10">  
				${record.id}
				<form:input type="hidden" path="id"/>
				<form:input class="form-control" path="id"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${CAPTION}</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path="caption"/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${ FFF }</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path=" fff "/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${ FFF }</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path=" fff "/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${ FFF }</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path=" fff "/>
			</div>
		</div>

		<div class="form-group">
			<label  class="col-sm-2 control-label" for="caption">${ FFF }</label>
			<div class="col-sm-10"> 
				<form:input class="form-control" path=" fff "/>
			</div>
		</div>
	</div>

	<div class="box-footer">
		<a href="#" onclick="doAjaxPost(' ttt /edit');" class="btn btn-primary">${SAVE}</a>
		<a href="#" onclick="doAjaxGet('${prevpage}');" class="btn btn-warning">${CANCEL}</a>
	</div>

	</form:form>
</div>

	public static IAbstractService[] lookupServices = null;
	public static String[] lookupServiceLists = null;

		if (lookupServices == null) {
			lookupServices = new IAbstractService[1];
			lookupServices[0] = 
		}


		List list = findAll();
		Map<String, String> map = new HashMap<String, String>();
		for(T x : list) {
			map.put(x.getId(), x.getCaption());
		}
		return map;


APPS.XXGAMA_ADSA_PERSONEL_V
APPS.XXGAMA_ADSA_ORGANIZATION_V


	public static final String rootMapping = "";
	public static final String listView = rootMapping + "List";
	public static final String editView = rootMapping + "Edit";
	public static final String showView = rootMapping + "Show";
	public static final String selectView = rootMapping + "Select";


@Transient
public static String tableName() {
	return tableName;
}




Adet	Ad	Birim
Bdn	Bdn	Aðýrlýk
Bi-Weekly	BWK	Period
Daily	DAY	Period
Each	EA	Birim
Half Yearly	HYR	Period
Kilogram	Kg	Aðýrlýk
Kilometre	Km	Uzunluk
Litre	Lt	Aðýrlýk
Lot	Lot	Birim
Metre	m	Uzunluk
Metreküp	M3	Birim
Monthly	MTH	Period
Quarterly	QTR	Period
Saat	s	Period
Set	Set	Birim
Ton	Ton	Aðýrlýk
Weekly	WK	Period
Yearly	YR	Period
square meter	M2	Uzunluk



PERSON_ID              NOT NULL NUMBER(10)     
FIRST_NAME                      VARCHAR2(150)  
LAST_NAME              NOT NULL VARCHAR2(150)  
EMAIL_ADDRESS                   VARCHAR2(240)  
NATIONALITY                     VARCHAR2(80)   
EMPLOYEE_NUMBER                 VARCHAR2(30)   
BIRINCI_AMIR_PERSON_ID          VARCHAR2(150)  
BIRINCI_AMIR                    VARCHAR2(4000) 
ORGANZIATION                    VARCHAR2(240)  
ORGANIZATION_ID                 NUMBER(15)     
LOCATION_ORGANIZATION           VARCHAR2(60)   
LOCATION_ID                     NUMBER(15)     
POSITION_NAME                   VARCHAR2(60)   
POSITION_ORGANIZASYONU          VARCHAR2(60)   
SGK_DURUMU                      VARCHAR2(60)   
POSITION_GMY                    VARCHAR2(150)  
POSITION_MUDURLUK               VARCHAR2(150)  
POSITION_BIRIM                  VARCHAR2(150)  
ANA_POZISYON                    VARCHAR2(150)  
KADRO                           VARCHAR2(700)  
KATEGORI                        VARCHAR2(30)   


CREATE TABLE COMPANY (
  COMPANY_ID             NOT NULL NUMBER(8),
  CAPTION                NOT NULL VARCHAR2(250),
  MASTER_COMPANY                  NUMBER(8),
  ADDRESS                         VARCHAR2(250),
  CITY                            VARCHAR2(40),
  STATE                           VARCHAR2(40),
  COUNTRY                         CHAR(2)
);

ALTER TABLE COMPANY
  ADD CONSTRAINT COMPANY_PK
      PRIMARY KEY (COMPANY_ID);

CREATE TABLE ORGANIZATION (
  ORGANIZATION_ID        NOT NULL NUMBER(8),
  CAPTION                NOT NULL VARCHAR2(250),
  COMPANY_ID             NOT NULL NUMBER(8)
);

ALTER TABLE ORGANIZATION
  ADD CONSTRAINT ORGANIZATION_PK
      PRIMARY KEY (ORGANIZATION_ID);

ALTER TABLE ORGANIZATION
  ADD CONSTRAINT ORGANIZATION_FK1
      FOREIGN KEY (COMPANY_ID)
      REFERENCES COMPANY (COMPANY_ID);



CREATE TABLE POSITION_TYPE (
  POSTYPE                NOT NULL VARCHAR2(30),
  CAPTION                NOT NULL VARCHAR2(250)
);

ALTER TABLE POSITION_TYPE
  ADD CONSTRAINT POSITION_TYPE_PK
      PRIMARY KEY (POSTYPE);



CREATE TABLE POSITION (
  POSITION_ID            NOT NULL NUMBER(8),
  ORGANIZATION_ID        NOT NULL NUMBER(8),
  POSTYPE                NOT NULL VARCHAR2(30),
  PARENT_POSITION                 NUMBER(8)
);

ALTER TABLE POSITION
  ADD CONSTRAINT POSITION_PK
      PRIMARY KEY (POSITION_ID);

ALTER TABLE POSITION
  ADD CONSTRAINT POSITION_FK1
      FOREIGN KEY (ORGANIZATION_ID)
      REFERENCES ORGANIZATION (ORGANIZATION_ID);

ALTER TABLE POSITION
  ADD CONSTRAINT POSITION_FK2
      FOREIGN KEY (POSTYPE)
      REFERENCES POSITION_TYPE (POSTYPE);



CREATE TABLE PERSON (
  PERSON_ID              NOT NULL NUMBER(11),
  FIRST_NAME             NOT NULL VARCHAR2(40),
  LAST_NAME              NOT NULL VARCHAR2(40),
  EMAIL_ADDRESS                   VARCHAR2(250),
  NATIONALITY                     CHAR(2)   
);

ALTER TABLE PERSON
  ADD CONSTRAINT PERSON_PK
      PRIMARY KEY (PERSON_ID);



CREATE TABLE PERSON_ASIGNMENT (
  PERSON_ID              NOT NULL NUMBER(11),
  POSITION_ID            NOT NULL NUMBER(8),
  BEGDA                  NOT NULL DATE,
  ENDDA                           DATE,
  ORGANIZATION_ID                 NUMBER(8),
  SUPERVISOR                      NUMBER(11)
);

ALTER TABLE PERSON_ASIGNMENT
  ADD CONSTRAINT PERSON_ASIGNMENT_PK
      PRIMARY KEY (PERSON_ID, POSITION_ID, BEGDA);

ALTER TABLE PERSON_ASIGNMENT
  ADD CONSTRAINT PERSON_ASIGNMENT_FK1
      FOREIGN KEY (PERSON_ID)
      REFERENCES PERSON (PERSON_ID);

ALTER TABLE PERSON_ASIGNMENT
  ADD CONSTRAINT PERSON_ASIGNMENT_FK2
      FOREIGN KEY (POSITION_ID)
      REFERENCES POSITION (POSITION_ID);

ALTER TABLE PERSON_ASIGNMENT
  ADD CONSTRAINT PERSON_ASIGNMENT_FK3
      FOREIGN KEY (ORGANIZATION_ID)
      REFERENCES ORGANIZATION (ORGANIZATION_ID);

ALTER TABLE PERSON_ASIGNMENT
  ADD CONSTRAINT PERSON_ASIGNMENT_FK4
      FOREIGN KEY (SUPERVISOR)
      REFERENCES PERSON (PERSON_ID);
