/*
package app.citta.utilities.GeneralizedModules.Firebase.Reciever;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter
{
    private SQLiteDatabase sqldb;
    private DBHelper dbHelper;
    private Context context;

    public DBAdapter(Context c)
    {
        context = c;
    }


    // Function to Open Database Connection in READ-ONLY Mode

    private DBAdapter openToRead() throws SQLException
    {
        dbHelper = new DBHelper(context);
        sqldb = dbHelper.getReadableDatabase();
        return this;
    }


    //Function to Open Database Connection in READ-WRITE Mode

    private DBAdapter openToWrite() throws SQLException
    {
        dbHelper = new DBHelper(context);
        sqldb = dbHelper.getWritableDatabase();
        return this;
    }


    // Function to Close Database Connection
    public void closeConnection()
    {
        dbHelper.close();
    }


    // Fetch USER_MASTER Data for Log In

    public Cursor FetchUserInfo(String strExeCode, String strPwd, String IMEI_No)
    {
        openToRead();
        Cursor c = null;
        String query1 = " SELECT * FROM USER_MAS WHERE EXECODE = '"+strExeCode+"' AND IMEI_NO = '"+IMEI_No+"' AND PASSWORD = '"+strPwd+"' ";

        try
        {
            c = sqldb.rawQuery(query1,null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Get USER_MASTER Data by ExeCode and IMEI Number

    public Cursor GetUserMasterDataByExeCodeAndIMEI(String strExeCode, String IMEI_No)
    {
        openToRead();
        Cursor c = null;
        String query1 = " SELECT * FROM USER_MAS WHERE EXECODE = '"+strExeCode+"' AND IMEI_NO = '"+IMEI_No+"' ";

        try
        {
            c = sqldb.rawQuery(query1,null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }





    // Method to Insert the USER_MASTER Data

    public long SaveNewUserMasterData(String userId, String executRefId, String executiveCode, String isAgency, String userName, String passWord, String imeiNumber,
                                      String isActiveUser, String isOnlineUser, String collectionLimit, String dayBeginTime, String dayEndTime, String minPlanVisitPerDay,
                                      String maxPlanVisitPerDay, String maxPtpAllowedPerMonth, String eligibleForPartialDeposit, String partialAmount, String title, String firstName,
                                      String middleName, String lastName, String aliasName, String gender, String maritalStatus, String email, String companyEmail, String phoneNo,
                                      String mobileNo, String faxNo, String birthDate, String joinDate, String leftDate, String bloodGroup, String isAdmin, String isVarified,
                                      String departmentId, String designationId, String levelId, String roleId, String lastLoginDate, String lastLoginBrowser, String lastLoginOs,
                                      String lastloginIp, String lastLoginDevice, String countryId, String stateId, String cityId, String areaId, String address1, String address2,
                                      String address3, String city, String postCode, String timeZone, String accountNo, String accountType, String bankName, String ifscCode, String branchAddress,
                                      String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("ID", userId);
            contentValues.put("EXECUT_REF_ID", executRefId);
            contentValues.put("EXECODE", executiveCode);
            contentValues.put("IS_AGENCY", isAgency);
            contentValues.put("USERNAME", userName);
            contentValues.put("PASSWORD", passWord);
            contentValues.put("IMEI_NO", imeiNumber);
            contentValues.put("IS_ACTIVE_USER", isActiveUser);
            contentValues.put("IS_ONLINE_USER", isOnlineUser);
            contentValues.put("COLLECTION_LIMIT", collectionLimit);
            contentValues.put("DAY_BEGIN_TIME", dayBeginTime);
            contentValues.put("DAY_END_TIME", dayEndTime);
            contentValues.put("MIN_PLAN_VISIT_PER_DAY", minPlanVisitPerDay);
            contentValues.put("MAX_PLAN_VISIT_PER_DAY", maxPlanVisitPerDay);
            contentValues.put("MAX_PTP_ALLOWED_PER_MONTH", maxPtpAllowedPerMonth);
            contentValues.put("IS_ELIGIBLE_FOR_PARTIAL_DEPOSIT", eligibleForPartialDeposit);
            contentValues.put("PARTIAL_AMOUNT_LIMIT", partialAmount);
            contentValues.put("TITLE", title);
            contentValues.put("FIRST_NAME", firstName);
            contentValues.put("MIDDLE_NAME", middleName);
            contentValues.put("LAST_NAME", lastName);
            contentValues.put("ALIAS_NAME", aliasName);
            contentValues.put("GENDER", gender);
            contentValues.put("MARITAL_STATUS", maritalStatus);
            contentValues.put("EMAIL", email);
            contentValues.put("COMPANY_EMAIL", companyEmail);
            contentValues.put("PHONE_NO", phoneNo);
            contentValues.put("MOBILE_NO", mobileNo);
            contentValues.put("FAX_NO", faxNo);
            contentValues.put("BIRTH_DATE", birthDate);
            contentValues.put("JOIN_DATE", joinDate);
            contentValues.put("LEFT_DATE", leftDate);
            contentValues.put("BLOOD_GROUP", bloodGroup);
            contentValues.put("IS_ADMIN", isAdmin);
            contentValues.put("IS_VARIFIED", isVarified);
            contentValues.put("DEPARTMENT_ID", departmentId);
            contentValues.put("DESIGNATION_ID", designationId);
            contentValues.put("LEVEL_ID", levelId);
            contentValues.put("ROLE_ID", roleId);
            contentValues.put("LAST_LOGIN_DATE", lastLoginDate);
            contentValues.put("LAST_LOGIN_BROWSER", lastLoginBrowser);
            contentValues.put("LAST_LOGIN_OS", lastLoginOs);
            contentValues.put("LAST_LOGIN_IP", lastloginIp);
            contentValues.put("LAST_LOGIN_DEVICE", lastLoginDevice);
            contentValues.put("COUNTRY_ID", countryId);
            contentValues.put("STATE_ID", stateId);
            contentValues.put("CITY_ID", cityId);
            contentValues.put("AREA_ID", areaId);
            contentValues.put("ADDRESS_1", address1);
            contentValues.put("ADDRESS_2", address2);
            contentValues.put("ADDRESS_3", address3);
            contentValues.put("CITY", city);
            contentValues.put("POSTCODE", postCode);
            contentValues.put("TIMEZONE", timeZone);
            contentValues.put("ACCOUNT_NO", accountNo);
            contentValues.put("ACCOUNT_TYPE", accountType);
            contentValues.put("BANK_NAME", bankName);
            contentValues.put("IFSC_CODE", ifscCode);
            contentValues.put("BRANCH_ADDRESS", branchAddress);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);

            val = sqldb.insert(DBHelper.USER_MASTER, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to Update the USER_MASTER Data

    public long UpdateUserMasterData(String userId, String executRefId, String executiveCode, String isAgency, String userName, String passWord, String imeiNumber,
                                     String isActiveUser, String isOnlineUser, String collectionLimit, String dayBeginTime, String dayEndTime, String minPlanVisitPerDay,
                                     String maxPlanVisitPerDay, String maxPtpAllowedPerMonth, String eligibleForPartialDeposit, String partialAmount, String title, String firstName,
                                     String middleName, String lastName, String aliasName, String gender, String maritalStatus, String email, String companyEmail, String phoneNo,
                                     String mobileNo, String faxNo, String birthDate, String joinDate, String leftDate, String bloodGroup, String isAdmin, String isVarified,
                                     String departmentId, String designationId, String levelId, String roleId, String lastLoginDate, String lastLoginBrowser, String lastLoginOs,
                                     String lastloginIp, String lastLoginDevice, String countryId, String stateId, String cityId, String areaId, String address1, String address2,
                                     String address3, String city, String postCode, String timeZone, String accountNo, String accountType, String bankName, String ifscCode, String branchAddress,
                                     String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("ID", userId);
            contentValues.put("EXECUT_REF_ID", executRefId);
            contentValues.put("EXECODE", executiveCode);
            contentValues.put("IS_AGENCY", isAgency);
            contentValues.put("USERNAME", userName);
            contentValues.put("PASSWORD", passWord);
            contentValues.put("IMEI_NO", imeiNumber);
            contentValues.put("IS_ACTIVE_USER", isActiveUser);
            contentValues.put("IS_ONLINE_USER", isOnlineUser);
            contentValues.put("COLLECTION_LIMIT", collectionLimit);
            contentValues.put("DAY_BEGIN_TIME", dayBeginTime);
            contentValues.put("DAY_END_TIME", dayEndTime);
            contentValues.put("MIN_PLAN_VISIT_PER_DAY", minPlanVisitPerDay);
            contentValues.put("MAX_PLAN_VISIT_PER_DAY", maxPlanVisitPerDay);
            contentValues.put("MAX_PTP_ALLOWED_PER_MONTH", maxPtpAllowedPerMonth);
            contentValues.put("IS_ELIGIBLE_FOR_PARTIAL_DEPOSIT", eligibleForPartialDeposit);
            contentValues.put("PARTIAL_AMOUNT_LIMIT", partialAmount);
            contentValues.put("TITLE", title);
            contentValues.put("FIRST_NAME", firstName);
            contentValues.put("MIDDLE_NAME", middleName);
            contentValues.put("LAST_NAME", lastName);
            contentValues.put("ALIAS_NAME", aliasName);
            contentValues.put("GENDER", gender);
            contentValues.put("MARITAL_STATUS", maritalStatus);
            contentValues.put("EMAIL", email);
            contentValues.put("COMPANY_EMAIL", companyEmail);
            contentValues.put("PHONE_NO", phoneNo);
            contentValues.put("MOBILE_NO", mobileNo);
            contentValues.put("FAX_NO", faxNo);
            contentValues.put("BIRTH_DATE", birthDate);
            contentValues.put("JOIN_DATE", joinDate);
            contentValues.put("LEFT_DATE", leftDate);
            contentValues.put("BLOOD_GROUP", bloodGroup);
            contentValues.put("IS_ADMIN", isAdmin);
            contentValues.put("IS_VARIFIED", isVarified);
            contentValues.put("DEPARTMENT_ID", departmentId);
            contentValues.put("DESIGNATION_ID", designationId);
            contentValues.put("LEVEL_ID", levelId);
            contentValues.put("ROLE_ID", roleId);
            contentValues.put("LAST_LOGIN_DATE", lastLoginDate);
            contentValues.put("LAST_LOGIN_BROWSER", lastLoginBrowser);
            contentValues.put("LAST_LOGIN_OS", lastLoginOs);
            contentValues.put("LAST_LOGIN_IP", lastloginIp);
            contentValues.put("LAST_LOGIN_DEVICE", lastLoginDevice);
            contentValues.put("COUNTRY_ID", countryId);
            contentValues.put("STATE_ID", stateId);
            contentValues.put("CITY_ID", cityId);
            contentValues.put("AREA_ID", areaId);
            contentValues.put("ADDRESS_1", address1);
            contentValues.put("ADDRESS_2", address2);
            contentValues.put("ADDRESS_3", address3);
            contentValues.put("CITY", city);
            contentValues.put("POSTCODE", postCode);
            contentValues.put("TIMEZONE", timeZone);
            contentValues.put("ACCOUNT_NO", accountNo);
            contentValues.put("ACCOUNT_TYPE", accountType);
            contentValues.put("BANK_NAME", bankName);
            contentValues.put("IFSC_CODE", ifscCode);
            contentValues.put("BRANCH_ADDRESS", branchAddress);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);

            val = sqldb.update(DBHelper.USER_MASTER, contentValues," EXECODE = '" + executiveCode + "' AND IMEI_NO = '" + imeiNumber + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }


    // Get User Details to Show in Navigation Header

    public Cursor GetUserDetails(String strExeCode, String IMEI_No)
    {
        openToRead();
        Cursor c = null;
        String query1 = " SELECT * FROM USER_MAS WHERE EXECODE = '"+strExeCode+"' AND IMEI_NO = '"+IMEI_No+"' ";

        try
        {
            c = sqldb.rawQuery(query1,null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Get All User Details from USER_MASTER

    public Cursor GetUserMasterDetails()
    {
        openToRead();
        Cursor c = null;
        String query1 = " SELECT * FROM USER_MAS ";

        try
        {
            c = sqldb.rawQuery(query1,null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in TARGET_MASTER

    public Cursor checkForDuplicateTargetData(String id, String exeCode, String custCode)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM TRGMAST WHERE ID = '"+id+"' AND EXE_CODE = '"+exeCode+"' AND CUST_CODE = '"+custCode+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // METHOD TO INSERT DATA IN TARGET_MASTER TABLE

    public long insertTargetMasterData(String trgId, String compCode, String trgFrom, String trgTo, String tweek, String demandDate, String cenCode, String centre, String segCode, String segment, String soCode, String rdoCode, String roCode, String exeCode, String tlCode, String custCode, String custName, String gName, String g2Name, String itemName, String vehicleNo, String oDueInstNo, String dueInstAmount, String approdAmount, String capOs, String charges, String bucket, String type, String cat, String focus, String legal , String more6, String npa, String followUp, String caseType, String trgAmount, String loanVisit, String grntVisit, String instAch, String achFine, String achAmount, String achLvisit, String achGvisit, String trgLvisit, String trgGvisit, String rmCode, String rmName,
                                       String nextDate, String status, String mark, String ptp, String ptpDate, String ptacAmount, String ptpFrom, String ptpTo, String entDate, String rPhone, String mPhone, String oPhone, String userName, String sysDate, String sysName, String bookSrNo, String customerType, String caseRemark, String remarkOfReturnForFastColl, String chqDtM1, String chqNoM1, String rtnRsnM1, String chqDtM2, String chqNoM2, String rtnRsnM2, String chqDtM3, String chqNoM3, String rtnRsnM3, String ledgBal, String receivableInst, String penalInt, String lateCharge, String obcCharge, String insuCharge, String dealerName, String marketingExecutive, String loanDate, String instDate, String liDate, String loanAmount,
                                       String lastExecutive, String rBlkNo, String rAdd1, String rAdd2, String rAdd3, String rCity, String rPin, String recoveryCentre, String state, String rtnChq, String engineNo, String chasisNo, String rcBookRved, String hpRved, String billRved, String coverRved, String insuRved, String lastOdInst, String recvInst, String recvPercentage, String recvPerGrp, String inst1Amount, String noofInst, String grAdd1, String grAdd2, String grAdd3, String grCity, String grPin, String g2rAdd1, String g2rAdd2, String g2rAdd3, String g2rCity, String g2rPin, String oName, String year, String nonStarterCust, String pdcStatus, String liDateStatus, String grpsegment, String migration, String odGroup, String collType, String theme,
                                       String nSegment, String odBkt, String nBkt, String bkt, String grpNoOfTimePayRved, String totalPayRved, String noOfTimePayRved, String averagePayRved, String criminalCivilLegalAcctNo, String stageMarking, String stageCriminal, String claimAmountCriminal, String caseFileDateCriminal, String nextDateCriminal, String nextDateStatusCriminal, String nextDateBifercationCriminal, String civilAccountNo, String stageCivil, String claimAmountCivil, String caseFileDateCivil, String nextDateCivil, String nextDatestatusCivil, String nextDateBifercationCivil, String arbitDate, String arbitClaimAmt, String newStage, String awardRvedDate)
    {
        openToWrite();
        long val = 0;
        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("ID", trgId);
            contentValues.put("COMP_CODE", compCode);
            contentValues.put("TRG_FROM", trgFrom);
            contentValues.put("TRG_TO", trgTo);
            contentValues.put("WEEK", tweek);
            contentValues.put("DEMDDT", demandDate);
            contentValues.put("CEN_CODE", cenCode);
            contentValues.put("CENTRE", centre);
            contentValues.put("SEG_CODE", segCode);
            contentValues.put("SEGMENT", segment);
            contentValues.put("SO_CODE", soCode);
            contentValues.put("RDO_CODE", rdoCode);
            contentValues.put("RO_CODE", roCode);
            contentValues.put("EXE_CODE", exeCode);
            contentValues.put("TL_CODE", tlCode);
            contentValues.put("CUST_CODE", custCode);
            contentValues.put("CUST_NAME", custName);
            contentValues.put("G_NAME", gName);
            contentValues.put("G2_NAME", g2Name);
            contentValues.put("ITEM_NAME", itemName);
            contentValues.put("VEH_NO", vehicleNo);
            contentValues.put("ODUEINSTNO", oDueInstNo);
            contentValues.put("DUEINSTAMT", dueInstAmount);
            contentValues.put("APRROD_AMT", approdAmount);
            contentValues.put("CAP_OS", capOs);
            contentValues.put("CHARGES", charges);
            contentValues.put("BUCKET", bucket);
            contentValues.put("TYPE", type);
            contentValues.put("CAT", cat);
            contentValues.put("FOCUS", focus);
            contentValues.put("LEGAL", legal);
            contentValues.put("MORE6", more6);
            contentValues.put("NPA", npa);
            contentValues.put("FOLLOWUP", followUp);
            contentValues.put("CASE_TYPE", caseType);
            contentValues.put("TRG_AMT", trgAmount);
            contentValues.put("LOAN_VISIT", loanVisit);
            contentValues.put("GRNT_VISIT", grntVisit);
            contentValues.put("INST_ACH", instAch);
            contentValues.put("ACH_FINE", achFine);
            contentValues.put("ACH_AMT", achAmount);
            contentValues.put("ACH_LVISIT", achLvisit);
            contentValues.put("ACH_GVISIT", achGvisit);
            contentValues.put("TRG_LVISIT", trgLvisit);
            contentValues.put("TRG_GVISIT", trgGvisit);
            contentValues.put("RM_CODE", rmCode);
            contentValues.put("RM_NAME", rmName);
            contentValues.put("NEXT_DATE", nextDate);
            contentValues.put("STATUS", status);
            contentValues.put("MARK", mark);
            contentValues.put("PTP", ptp);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("PTAC_AMT", ptacAmount);
            contentValues.put("PTP_FROM", ptpFrom);
            contentValues.put("PTP_TO", ptpTo);
            contentValues.put("ENT_DATE", entDate);
            contentValues.put("R_PHONE", rPhone);
            contentValues.put("M_PHONE", mPhone);
            contentValues.put("O_PHONE", oPhone);
            contentValues.put("USER_NAME", userName);
            contentValues.put("SYS_DATE", sysDate);
            contentValues.put("SYS_NAME", sysName);
            contentValues.put("BOOK_SR_NO", bookSrNo);
            contentValues.put("CUSTOMER_TYPE", customerType);
            contentValues.put("CASE_REMARKS", caseRemark);
            contentValues.put("REMARKS_OF_RETURN_FOR_FAST_COLLECTION", remarkOfReturnForFastColl);
            contentValues.put("CHQDT_M1", chqDtM1);
            contentValues.put("CHQNO_M1", chqNoM1);
            contentValues.put("RTNRSN_M1", rtnRsnM1);
            contentValues.put("CHQDT_M2", chqDtM2);
            contentValues.put("CHQNO_M2", chqNoM2);
            contentValues.put("RTNRSN_M2", rtnRsnM2);
            contentValues.put("CHQDT_M3", chqDtM3);
            contentValues.put("CHQNO_M3", chqNoM3);
            contentValues.put("RTNRSN_M3", rtnRsnM3);
            contentValues.put("LEDG_BAL", ledgBal);
            contentValues.put("RECEIVABLE_INSTALMENT", receivableInst);
            contentValues.put("PENAL_INT", penalInt);
            contentValues.put("LATE_CHARGE", lateCharge);
            contentValues.put("OBC_CHARGE", obcCharge);
            contentValues.put("INSU_CHARGE", insuCharge);
            contentValues.put("DEALER_NAME", dealerName);
            contentValues.put("MARKETING_EXECUTIVE", marketingExecutive);
            contentValues.put("LOAN_DATE", loanDate);
            contentValues.put("INST_DT", instDate);
            contentValues.put("LI_DATE", liDate);
            contentValues.put("LOAN_AMT", loanAmount);
            contentValues.put("LAST_EXECUTIVE", lastExecutive);
            contentValues.put("R_BLK_NO", rBlkNo);
            contentValues.put("R_ADD1", rAdd1);
            contentValues.put("R_ADD2", rAdd2);
            contentValues.put("R_ADD3", rAdd3);
            contentValues.put("R_CITY", rCity);
            contentValues.put("R_PIN", rPin);
            contentValues.put("RECOVERY_CENTRE", recoveryCentre);
            contentValues.put("STATE", state);
            contentValues.put("RTN_CHQ", rtnChq);
            contentValues.put("ENGINE_NO", engineNo);
            contentValues.put("CHASIS_NO", chasisNo);
            contentValues.put("RCBOOK_RECVD", rcBookRved);
            contentValues.put("HP_RECVD", hpRved);
            contentValues.put("BILL_RECVD", billRved);
            contentValues.put("COVER_RECVD", coverRved);
            contentValues.put("INSU_RECVD", insuRved);
            contentValues.put("LAST_OD_INST", lastOdInst);
            contentValues.put("RECV_INST", recvInst);
            contentValues.put("RECV_PER", recvPercentage);
            contentValues.put("RECV_PER_GRP", recvPerGrp);
            contentValues.put("INST1_AMT", inst1Amount);
            contentValues.put("NOOF_INST", noofInst);
            contentValues.put("G_R_ADD1", grAdd1);
            contentValues.put("G_R_ADD2", grAdd2);
            contentValues.put("G_R_ADD3", grAdd3);
            contentValues.put("G_R_CITY", grCity);
            contentValues.put("G_R_PIN", grPin);
            contentValues.put("G2_R_ADD1", g2rAdd1);
            contentValues.put("G2_R_ADD2", g2rAdd2);
            contentValues.put("G2_R_ADD3", g2rAdd3);
            contentValues.put("G2_R_CITY", g2rCity);
            contentValues.put("G2_R_PIN", g2rPin);
            contentValues.put("O_NAME", oName);
            contentValues.put("YEAR", year);
            contentValues.put("NON_STARTER_CUSTOMER", nonStarterCust);
            contentValues.put("PDC_STATUS", pdcStatus);
            contentValues.put("LIDATE_STATUS", liDateStatus);
            contentValues.put("GROUP_SEGMENT", grpsegment);
            contentValues.put("MIGRATION", migration);
            contentValues.put("OD_GROUP", odGroup);
            contentValues.put("COLLTYPE", collType);
            contentValues.put("THEME", theme);
            contentValues.put("N_SEGMENT", nSegment);
            contentValues.put("OD_BKT", odBkt);
            contentValues.put("N_BKT", nBkt);
            contentValues.put("BKT", bkt);
            contentValues.put("GROUP_NO_OF_TIME_PAY_RVED", grpNoOfTimePayRved);
            contentValues.put("TOTAL_PAYMENT_RVED", totalPayRved);
            contentValues.put("NO_OF_TIME_PAY_RVED", noOfTimePayRved);
            contentValues.put("AVERAGE_PAY_RVED", averagePayRved);
            contentValues.put("CRIMINAL_CIVIL_LEGAL_ACCOUNT_NO", criminalCivilLegalAcctNo);
            contentValues.put("STAGE_MARKING", stageMarking);
            contentValues.put("STAGE_CRIMINAL", stageCriminal);
            contentValues.put("CLAIM_AMT_CRIMINAL", claimAmountCriminal);
            contentValues.put("CASE_FILE_DATE_CRIMINAL", caseFileDateCriminal);
            contentValues.put("NEXT_DATE_CRIMINAL", nextDateCriminal);
            contentValues.put("NEXT_DATE_STATUS_CRIMINAL", nextDateStatusCriminal);
            contentValues.put("NEXT_DATE_BIFERCATION_CRIMINAL", nextDateBifercationCriminal);
            contentValues.put("CIVIL_ACCOUNT_NO", civilAccountNo);
            contentValues.put("STAGE_CIVIL", stageCivil);
            contentValues.put("CLAIM_AMT_CIVIL", claimAmountCivil);
            contentValues.put("CASE_FILE_DATE_CIVIL", caseFileDateCivil);
            contentValues.put("NEXT_DATE_CIVIL", nextDateCivil);
            contentValues.put("NEXT_DATE_STATUS_CIVIL", nextDatestatusCivil);
            contentValues.put("NEXT_DATE_BIFERCATION_CIVIL", nextDateBifercationCivil);
            contentValues.put("ARBITRATION_DATE", arbitDate);
            contentValues.put("ARBITRATION_CLAIM_AMOUNT", arbitClaimAmt);
            contentValues.put("NEW_STAGE", newStage);
            contentValues.put("AWARD_RECEIVED_DATE", awardRvedDate);

            val = sqldb.insert(DBHelper.TARGET_MASTER, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }

    // Method to update TARGET_MASTER Data in SQLite Database

    public long updateTargetMasterData(String trgId, String compCode, String trgFrom, String trgTo, String tweek, String demandDate, String cenCode, String centre, String segCode, String segment, String soCode, String rdoCode, String roCode, String exeCode, String tlCode, String custCode, String custName, String gName, String g2Name, String itemName, String vehicleNo, String oDueInstNo, String dueInstAmount, String approdAmount, String capOs, String charges, String bucket, String type, String cat, String focus, String legal , String more6, String npa, String followUp, String caseType, String trgAmount, String loanVisit, String grntVisit, String instAch, String achFine, String achAmount, String achLvisit, String achGvisit, String trgLvisit, String trgGvisit, String rmCode, String rmName,
                                       String nextDate, String status, String mark, String ptp, String ptpDate, String ptacAmount, String ptpFrom, String ptpTo, String entDate, String rPhone, String mPhone, String oPhone, String userName, String sysDate, String sysName, String bookSrNo, String customerType, String caseRemark, String remarkOfReturnForFastColl, String chqDtM1, String chqNoM1, String rtnRsnM1, String chqDtM2, String chqNoM2, String rtnRsnM2, String chqDtM3, String chqNoM3, String rtnRsnM3, String ledgBal, String receivableInst, String penalInt, String lateCharge, String obcCharge, String insuCharge, String dealerName, String marketingExecutive, String loanDate, String instDate, String liDate, String loanAmount,
                                       String lastExecutive, String rBlkNo, String rAdd1, String rAdd2, String rAdd3, String rCity, String rPin, String recoveryCentre, String state, String rtnChq, String engineNo, String chasisNo, String rcBookRved, String hpRved, String billRved, String coverRved, String insuRved, String lastOdInst, String recvInst, String recvPercentage, String recvPerGrp, String inst1Amount, String noofInst, String grAdd1, String grAdd2, String grAdd3, String grCity, String grPin, String g2rAdd1, String g2rAdd2, String g2rAdd3, String g2rCity, String g2rPin, String oName, String year, String nonStarterCust, String pdcStatus, String liDateStatus, String grpsegment, String migration, String odGroup, String collType, String theme,
                                       String nSegment, String odBkt, String nBkt, String bkt, String grpNoOfTimePayRved, String totalPayRved, String noOfTimePayRved, String averagePayRved, String criminalCivilLegalAcctNo, String stageMarking, String stageCriminal, String claimAmountCriminal, String caseFileDateCriminal, String nextDateCriminal, String nextDateStatusCriminal, String nextDateBifercationCriminal, String civilAccountNo, String stageCivil, String claimAmountCivil, String caseFileDateCivil, String nextDateCivil, String nextDatestatusCivil, String nextDateBifercationCivil, String arbitDate, String arbitClaimAmt, String newStage, String awardRvedDate)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("ID", trgId);
            contentValues.put("COMP_CODE", compCode);
            contentValues.put("TRG_FROM", trgFrom);
            contentValues.put("TRG_TO", trgTo);
            contentValues.put("WEEK", tweek);
            contentValues.put("DEMDDT", demandDate);
            contentValues.put("CEN_CODE", cenCode);
            contentValues.put("CENTRE", centre);
            contentValues.put("SEG_CODE", segCode);
            contentValues.put("SEGMENT", segment);
            contentValues.put("SO_CODE", soCode);
            contentValues.put("RDO_CODE", rdoCode);
            contentValues.put("RO_CODE", roCode);
            contentValues.put("EXE_CODE", exeCode);
            contentValues.put("TL_CODE", tlCode);
            contentValues.put("CUST_CODE", custCode);
            contentValues.put("CUST_NAME", custName);
            contentValues.put("G_NAME", gName);
            contentValues.put("G2_NAME", g2Name);
            contentValues.put("ITEM_NAME", itemName);
            contentValues.put("VEH_NO", vehicleNo);
            contentValues.put("ODUEINSTNO", oDueInstNo);
            contentValues.put("DUEINSTAMT", dueInstAmount);
            contentValues.put("APRROD_AMT", approdAmount);
            contentValues.put("CAP_OS", capOs);
            contentValues.put("CHARGES", charges);
            contentValues.put("BUCKET", bucket);
            contentValues.put("TYPE", type);
            contentValues.put("CAT", cat);
            contentValues.put("FOCUS", focus);
            contentValues.put("LEGAL", legal);
            contentValues.put("MORE6", more6);
            contentValues.put("NPA", npa);
            contentValues.put("FOLLOWUP", followUp);
            contentValues.put("CASE_TYPE", caseType);
            contentValues.put("TRG_AMT", trgAmount);
            contentValues.put("LOAN_VISIT", loanVisit);
            contentValues.put("GRNT_VISIT", grntVisit);
            contentValues.put("INST_ACH", instAch);
            contentValues.put("ACH_FINE", achFine);
            contentValues.put("ACH_AMT", achAmount);
            contentValues.put("ACH_LVISIT", achLvisit);
            contentValues.put("ACH_GVISIT", achGvisit);
            contentValues.put("TRG_LVISIT", trgLvisit);
            contentValues.put("TRG_GVISIT", trgGvisit);
            contentValues.put("RM_CODE", rmCode);
            contentValues.put("RM_NAME", rmName);
            contentValues.put("NEXT_DATE", nextDate);
            contentValues.put("STATUS", status);
            contentValues.put("MARK", mark);
            contentValues.put("PTP", ptp);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("PTAC_AMT", ptacAmount);
            contentValues.put("PTP_FROM", ptpFrom);
            contentValues.put("PTP_TO", ptpTo);
            contentValues.put("ENT_DATE", entDate);
            contentValues.put("R_PHONE", rPhone);
            contentValues.put("M_PHONE", mPhone);
            contentValues.put("O_PHONE", oPhone);
            contentValues.put("USER_NAME", userName);
            contentValues.put("SYS_DATE", sysDate);
            contentValues.put("SYS_NAME", sysName);
            contentValues.put("BOOK_SR_NO", bookSrNo);
            contentValues.put("CUSTOMER_TYPE", customerType);
            contentValues.put("CASE_REMARKS", caseRemark);
            contentValues.put("REMARKS_OF_RETURN_FOR_FAST_COLLECTION", remarkOfReturnForFastColl);
            contentValues.put("CHQDT_M1", chqDtM1);
            contentValues.put("CHQNO_M1", chqNoM1);
            contentValues.put("RTNRSN_M1", rtnRsnM1);
            contentValues.put("CHQDT_M2", chqDtM2);
            contentValues.put("CHQNO_M2", chqNoM2);
            contentValues.put("RTNRSN_M2", rtnRsnM2);
            contentValues.put("CHQDT_M3", chqDtM3);
            contentValues.put("CHQNO_M3", chqNoM3);
            contentValues.put("RTNRSN_M3", rtnRsnM3);
            contentValues.put("LEDG_BAL", ledgBal);
            contentValues.put("RECEIVABLE_INSTALMENT", receivableInst);
            contentValues.put("PENAL_INT", penalInt);
            contentValues.put("LATE_CHARGE", lateCharge);
            contentValues.put("OBC_CHARGE", obcCharge);
            contentValues.put("INSU_CHARGE", insuCharge);
            contentValues.put("DEALER_NAME", dealerName);
            contentValues.put("MARKETING_EXECUTIVE", marketingExecutive);
            contentValues.put("LOAN_DATE", loanDate);
            contentValues.put("INST_DT", instDate);
            contentValues.put("LI_DATE", liDate);
            contentValues.put("LOAN_AMT", loanAmount);
            contentValues.put("LAST_EXECUTIVE", lastExecutive);
            contentValues.put("R_BLK_NO", rBlkNo);
            contentValues.put("R_ADD1", rAdd1);
            contentValues.put("R_ADD2", rAdd2);
            contentValues.put("R_ADD3", rAdd3);
            contentValues.put("R_CITY", rCity);
            contentValues.put("R_PIN", rPin);
            contentValues.put("RECOVERY_CENTRE", recoveryCentre);
            contentValues.put("STATE", state);
            contentValues.put("RTN_CHQ", rtnChq);
            contentValues.put("ENGINE_NO", engineNo);
            contentValues.put("CHASIS_NO", chasisNo);
            contentValues.put("RCBOOK_RECVD", rcBookRved);
            contentValues.put("HP_RECVD", hpRved);
            contentValues.put("BILL_RECVD", billRved);
            contentValues.put("COVER_RECVD", coverRved);
            contentValues.put("INSU_RECVD", insuRved);
            contentValues.put("LAST_OD_INST", lastOdInst);
            contentValues.put("RECV_INST", recvInst);
            contentValues.put("RECV_PER", recvPercentage);
            contentValues.put("RECV_PER_GRP", recvPerGrp);
            contentValues.put("INST1_AMT", inst1Amount);
            contentValues.put("NOOF_INST", noofInst);
            contentValues.put("G_R_ADD1", grAdd1);
            contentValues.put("G_R_ADD2", grAdd2);
            contentValues.put("G_R_ADD3", grAdd3);
            contentValues.put("G_R_CITY", grCity);
            contentValues.put("G_R_PIN", grPin);
            contentValues.put("G2_R_ADD1", g2rAdd1);
            contentValues.put("G2_R_ADD2", g2rAdd2);
            contentValues.put("G2_R_ADD3", g2rAdd3);
            contentValues.put("G2_R_CITY", g2rCity);
            contentValues.put("G2_R_PIN", g2rPin);
            contentValues.put("O_NAME", oName);
            contentValues.put("YEAR", year);
            contentValues.put("NON_STARTER_CUSTOMER", nonStarterCust);
            contentValues.put("PDC_STATUS", pdcStatus);
            contentValues.put("LIDATE_STATUS", liDateStatus);
            contentValues.put("GROUP_SEGMENT", grpsegment);
            contentValues.put("MIGRATION", migration);
            contentValues.put("OD_GROUP", odGroup);
            contentValues.put("COLLTYPE", collType);
            contentValues.put("THEME", theme);
            contentValues.put("N_SEGMENT", nSegment);
            contentValues.put("OD_BKT", odBkt);
            contentValues.put("N_BKT", nBkt);
            contentValues.put("BKT", bkt);
            contentValues.put("GROUP_NO_OF_TIME_PAY_RVED", grpNoOfTimePayRved);
            contentValues.put("TOTAL_PAYMENT_RVED", totalPayRved);
            contentValues.put("NO_OF_TIME_PAY_RVED", noOfTimePayRved);
            contentValues.put("AVERAGE_PAY_RVED", averagePayRved);
            contentValues.put("CRIMINAL_CIVIL_LEGAL_ACCOUNT_NO", criminalCivilLegalAcctNo);
            contentValues.put("STAGE_MARKING", stageMarking);
            contentValues.put("STAGE_CRIMINAL", stageCriminal);
            contentValues.put("CLAIM_AMT_CRIMINAL", claimAmountCriminal);
            contentValues.put("CASE_FILE_DATE_CRIMINAL", caseFileDateCriminal);
            contentValues.put("NEXT_DATE_CRIMINAL", nextDateCriminal);
            contentValues.put("NEXT_DATE_STATUS_CRIMINAL", nextDateStatusCriminal);
            contentValues.put("NEXT_DATE_BIFERCATION_CRIMINAL", nextDateBifercationCriminal);
            contentValues.put("CIVIL_ACCOUNT_NO", civilAccountNo);
            contentValues.put("STAGE_CIVIL", stageCivil);
            contentValues.put("CLAIM_AMT_CIVIL", claimAmountCivil);
            contentValues.put("CASE_FILE_DATE_CIVIL", caseFileDateCivil);
            contentValues.put("NEXT_DATE_CIVIL", nextDateCivil);
            contentValues.put("NEXT_DATE_STATUS_CIVIL", nextDatestatusCivil);
            contentValues.put("NEXT_DATE_BIFERCATION_CIVIL", nextDateBifercationCivil);
            contentValues.put("ARBITRATION_DATE", arbitDate);
            contentValues.put("ARBITRATION_CLAIM_AMOUNT", arbitClaimAmt);
            contentValues.put("NEW_STAGE", newStage);
            contentValues.put("AWARD_RECEIVED_DATE", awardRvedDate);

            
            val = sqldb.update(DBHelper.TARGET_MASTER, contentValues," ID = '" + trgId + "' AND EXE_CODE = '" + exeCode + "' AND CUST_CODE = '" + custCode + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in TEMP_PLAN_VISIT

    public Cursor checkForDuplicateTempPlanVisit(String userId, String trgId, String custCode)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM TEMP_PLAN_VISIT WHERE USER_ID = '"+userId+"' AND TARGET_MASTER_ID = '"+trgId+"' AND CUSTOMER_CODE = '"+custCode+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert TEMP_PLAN_VISIT Data in SQLite Database

    public long insertTempPlanVisitData(String uId, String trgId, String custCode, String visitType, String collectionAmt, String ptpDate)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("USER_ID", uId);
            contentValues.put("TARGET_MASTER_ID", trgId);
            contentValues.put("CUSTOMER_CODE", custCode);
            contentValues.put("VISIT_TYPE_PLANNED", visitType);
            contentValues.put("COLLECTION_PLANNED", collectionAmt);
            contentValues.put("PTP_DATE", ptpDate);

            val = sqldb.insert(DBHelper.TEMP_PLAN_VISIT, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }


    // Method to update TEMP_PLAN_VISIT Data in SQLite Database

    public long updateTempPlanVisitData(String uId, String trgId, String custCode, String visitType, String collectionAmt, String ptpDate)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("USER_ID", uId);
            contentValues.put("TARGET_MASTER_ID", trgId);
            contentValues.put("CUSTOMER_CODE", custCode);
            contentValues.put("VISIT_TYPE_PLANNED", visitType);
            contentValues.put("COLLECTION_PLANNED", collectionAmt);
            contentValues.put("PTP_DATE", ptpDate);

            
            val = sqldb.update(DBHelper.TEMP_PLAN_VISIT, contentValues," USER_ID = '" + uId + "' AND TARGET_MASTER_ID = '" + trgId + "' AND CUSTOMER_CODE = '" + custCode + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }


    // Method to Fetch the Record from TEMP_PLAN_VISIT Table

    public Cursor checkForTempPlanVisitRecord(String userId, String trgId)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM TEMP_PLAN_VISIT WHERE USER_ID = '"+userId+"' AND TARGET_MASTER_ID = '"+trgId+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in TARGET_MASTER

    public Cursor checkForFeedBackData(String id)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM FEEDBACK WHERE ID = '"+id+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Insert FEEDBACK Data in SQLite Database

    public long insertFeedBackData(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("ID", id);
            contentValues.put("FEEDBACK", name);
            contentValues.put("EXIST", exist);

            val = sqldb.insert(DBHelper.FEEDBACK, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }


    // Method to Update FEEDBACK Data in SQLite Database

    public long updateFeedBackData(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("ID", id);
            contentValues.put("FEEDBACK", name);
            contentValues.put("EXIST", exist);

            val = sqldb.update(DBHelper.FEEDBACK, contentValues," FEEDBACK = '" + name + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }


    // Method to check for duplicate entries in TARGET_MASTER

    public Cursor fetchFeedBack(String isExist)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM FEEDBACK WHERE EXIST = '"+isExist+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data frm TARGET_MASTER based on EXECUTIVE_CODE

    public Cursor getTargetMasterData(String exeCode)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM TRGMAST WHERE EXE_CODE = '"+exeCode+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }

    // Method to check for duplicate entries in PLAN_VISIT Table

    public Cursor checkForDuplicatePlanVisitData(String userId, String custCode, String trgMastId, String isDelete)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PLAN_VISIT WHERE USER_ID = '"+userId+"' AND CUSTOMER_CODE = '"+custCode+"' AND TARGET_MASTER_ID = '"+trgMastId+"' AND ISDELETE = '"+isDelete+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert PLAN_VISIT Data in SQLite Database

    public long insertPlanVisitData(String userId, String trgMastId, String custCode, String visitType, String collectionAmt, String caseType, String ptpDate, String entryDate)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("USER_ID", userId);
            contentValues.put("TARGET_MASTER_ID", trgMastId);
            contentValues.put("CUSTOMER_CODE", custCode);
            contentValues.put("VISIT_TYPE_PLANNED", visitType);
            contentValues.put("COLLECTION_PLANNED", collectionAmt);
            contentValues.put("CASE_TYPE", caseType);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("IS_ACTIVE_VISIT", 1);
            contentValues.put("IS_PLANNED_VISIT", 1);
            contentValues.put("BID", 0);
            contentValues.put("CID", 0);
            contentValues.put("ISACTIVE", 1);
            contentValues.put("ISDELETE", 0);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", 0);
            contentValues.put("ISSYNC", 0);
            contentValues.put("SID", 0);
            contentValues.put("NOTES", "");

            val = sqldb.insert(DBHelper.PLAN_VISIT, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }

    // Method to update PLAN_VISIT Data in SQLite Database

    public long updatePlanVisitData(String userId, String trgMastId, String custCode, String visitType, String collectionAmt, String caseType, String ptpDate, String entryDate)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("USER_ID", userId);
            contentValues.put("TARGET_MASTER_ID", trgMastId);
            contentValues.put("CUSTOMER_CODE", custCode);
            contentValues.put("VISIT_TYPE_PLANNED", visitType);
            contentValues.put("COLLECTION_PLANNED", collectionAmt);
            contentValues.put("CASE_TYPE", caseType);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("IS_ACTIVE_VISIT", 1);
            contentValues.put("IS_PLANNED_VISIT", 1);
            contentValues.put("BID", 0);
            contentValues.put("CID", 0);
            contentValues.put("ISACTIVE", 1);
            contentValues.put("ISDELETE", 0);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", 0);
            contentValues.put("ISSYNC", 0);
            contentValues.put("SID", 0);
            contentValues.put("NOTES", "");

            val = sqldb.update(DBHelper.PLAN_VISIT, contentValues," USER_ID = '" + userId + "' AND TARGET_MASTER_ID = '" + trgMastId + "' AND CUSTOMER_CODE = '" + custCode + "' AND ISDELETE = 0 ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }


    // Method to insert PLAN_VISIT Data in SQLite Database (UN-PLANNED)

    public long insertUnPlanVisitData(String userId, String trgMastId, String custCode, String visitType, String collectionAmt, String caseType, String ptpDate, String entryDate)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("USER_ID", userId);
            contentValues.put("TARGET_MASTER_ID", trgMastId);
            contentValues.put("CUSTOMER_CODE", custCode);
            contentValues.put("VISIT_TYPE_PLANNED", visitType);
            contentValues.put("COLLECTION_PLANNED", collectionAmt);
            contentValues.put("CASE_TYPE", caseType);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("IS_ACTIVE_VISIT", 1);
            contentValues.put("IS_PLANNED_VISIT", 0);
            contentValues.put("BID", 0);
            contentValues.put("CID", 0);
            contentValues.put("ISACTIVE", 1);
            contentValues.put("ISDELETE", 0);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", 0);
            contentValues.put("ISSYNC", 0);
            contentValues.put("SID", 0);
            contentValues.put("NOTES", "");
            
            val = sqldb.insert(DBHelper.PLAN_VISIT, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }


    // Method to update PLAN_VISIT Data in SQLite Database (UN_PLANNED)

    public long updateUnPlanVisitData(String userId, String trgMastId, String custCode, String visitType, String collectionAmt, String caseType, String ptpDate, String entryDate)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("USER_ID", userId);
            contentValues.put("TARGET_MASTER_ID", trgMastId);
            contentValues.put("CUSTOMER_CODE", custCode);
            contentValues.put("VISIT_TYPE_PLANNED", visitType);
            contentValues.put("COLLECTION_PLANNED", collectionAmt);
            contentValues.put("CASE_TYPE", caseType);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("IS_ACTIVE_VISIT", 1);
            contentValues.put("IS_PLANNED_VISIT", 0);
            contentValues.put("BID", 0);
            contentValues.put("CID", 0);
            contentValues.put("ISACTIVE", 1);
            contentValues.put("ISDELETE", 0);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", 0);
            contentValues.put("ISSYNC", 0);
            contentValues.put("SID", 0);
            contentValues.put("NOTES", "");

            val = sqldb.update(DBHelper.PLAN_VISIT, contentValues," USER_ID = '" + userId + "' AND TARGET_MASTER_ID = '" + trgMastId + "' AND CUSTOMER_CODE = '" + custCode + "' AND ISDELETE = 0 ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }


    // Method to Fetch Data from PLAN_VISIT based on USER_ID

    public Cursor getAllPlannedVisitData()
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PLAN_VISIT ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data from PLAN_VISIT_HISTORY

    public Cursor getAllPlannedVisitHistoryData()
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PLAN_VISIT_HISTORY ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data from VISIT_COLLECTION

    public Cursor getAllVisitCollectionData()
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM VISIT_COLLECTION ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Delete Data from PLAN_VISIT

    public void deletePlanVisitData()
    {
        try
        {
            openToWrite();
            sqldb.delete(DBHelper.PLAN_VISIT, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    // Delete Data from PLAN_VISIT_HISTORY

    public void deletePlanVisitHistoryData()
    {
        try
        {
            openToWrite();
            sqldb.delete(DBHelper.PLAN_VISIT_HISTORY, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    // Delete Data from TEMP_PLAN_VISIT

    public void deleteTempPlanVisitData()
    {
        try
        {
            openToWrite();
            sqldb.delete(DBHelper.TEMP_PLAN_VISIT, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    // Empty USER_MASTER Table

    public void emptyUserMasterData()
    {
        try
        {
            openToWrite();
            sqldb.delete(DBHelper.USER_MASTER, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    // Delete Data from TARGET_MASTER

    public void deletetargetMasterData()
    {
        try
        {
            openToWrite();
            sqldb.delete(DBHelper.TARGET_MASTER, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Delete Data from VISIT_COLLECTION

    public void deleteVisitCollectionData()
    {
        try
        {
            openToWrite();
            sqldb.delete(DBHelper.VISIT_COLLECTION, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    // Delete Data from BANK_MASTER_MFSL

    public void deleteBankMasterMFSLData()
    {
        try
        {
            openToWrite();
            sqldb.delete(DBHelper.BANK_MASTER_MFSL, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    // Empty TIME_MASTER Table

    public void emptyTimeMasterData()
    {
        try
        {
            openToWrite();
            sqldb.delete(DBHelper.TIME_MASTER, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    // Method to Fetch Data frm TARGET_MASTER based on EXECUTIVE_CODE

    public Cursor getActivePlannedVisitData(String userId)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PLAN_VISIT WHERE USER_ID = '"+userId+"' AND IS_ACTIVE_VISIT = 1 AND IS_PLANNED_VISIT = 1 AND ISDELETE = 0 ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data frm TARGET_MASTER based on EXECUTIVE_CODE

    public Cursor fetchAllFeedbackData()
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM FEEDBACK ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in PLAN_VISIT Table

    public Cursor checkForDuplicateVisitCollectionData(String planVisitId)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM VISIT_COLLECTION WHERE PLAN_VISIT_ID = '"+planVisitId+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert PLAN_VISIT Data in SQLite Database

    public long insertVisitCollectionData(String planId, String visitType, String payType, String amt, String chqNo, String bName, String ddNo, String micrCode, String ifscCode, String bankAddress, String trnsDate, String ptpMode, String ptpAmt, String ptpdate, String prInfotoPolice, String pstInfotoPolice, String loanamt, String roi, String emi, String tenor, String remarkId, String entryDate)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("PLAN_VISIT_ID", planId);
            contentValues.put("VISIT_TYPE_ACTUAL", visitType);
            contentValues.put("PAYMENT_TYPE", payType);
            contentValues.put("AMOUNT", amt);
            contentValues.put("CHEQUENO", chqNo);
            contentValues.put("BANKNAME", bName);
            contentValues.put("DDNO", ddNo);
            contentValues.put("MICRCODE", micrCode);
            contentValues.put("IFSCCODE", ifscCode);
            contentValues.put("BANKADDRESS", bankAddress);
            contentValues.put("TRANSACTION_DATE", trnsDate);
            contentValues.put("PTP_MODE", ptpMode);
            contentValues.put("PTP_AMT", ptpAmt);
            contentValues.put("PTP_DATE", ptpdate);
            contentValues.put("PRIOR_INFO_TO_POLICE", prInfotoPolice);
            contentValues.put("POST_INFO_TO_POLICE", pstInfotoPolice);
            contentValues.put("LOAN_AMT", loanamt);
            contentValues.put("RATE_OF_INTEREST", roi);
            contentValues.put("EMI", emi);
            contentValues.put("TENURE", tenor);
            contentValues.put("REMARKID", remarkId);
            contentValues.put("BID", 0);
            contentValues.put("CID", 0);
            contentValues.put("ISACTIVE", 1);
            contentValues.put("ISDELETE", 0);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", 0);
            contentValues.put("ISSYNC", 0);
            contentValues.put("SID", 0);
            contentValues.put("NOTES", "");

            val = sqldb.insert(DBHelper.VISIT_COLLECTION, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }

    // Method to update PLAN_VISIT Data in SQLite Database

    public long updateVisitCollectionData(String planId, String visitType, String payType, String amt, String chqNo, String bName, String ddNo, String micrCode, String ifscCode, String bankAddress, String trnsDate, String ptpMode, String ptpAmt, String ptpdate, String prInfotoPolice, String pstInfotoPolice, String loanamt, String roi, String emi, String tenor, String remarkId, String entryDate)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("PLAN_VISIT_ID", planId);
            contentValues.put("VISIT_TYPE_ACTUAL", visitType);
            contentValues.put("PAYMENT_TYPE", payType);
            contentValues.put("AMOUNT", amt);
            contentValues.put("CHEQUENO", chqNo);
            contentValues.put("BANKNAME", bName);
            contentValues.put("DDNO", ddNo);
            contentValues.put("MICRCODE", micrCode);
            contentValues.put("IFSCCODE", ifscCode);
            contentValues.put("BANKADDRESS", bankAddress);
            contentValues.put("TRANSACTION_DATE", trnsDate);
            contentValues.put("PTP_MODE", ptpMode);
            contentValues.put("PTP_AMT", ptpAmt);
            contentValues.put("PTP_DATE", ptpdate);
            contentValues.put("PRIOR_INFO_TO_POLICE", prInfotoPolice);
            contentValues.put("POST_INFO_TO_POLICE", pstInfotoPolice);
            contentValues.put("LOAN_AMT", loanamt);
            contentValues.put("RATE_OF_INTEREST", roi);
            contentValues.put("EMI", emi);
            contentValues.put("TENURE", tenor);
            contentValues.put("REMARKID", remarkId);
            contentValues.put("BID", 0);
            contentValues.put("CID", 0);
            contentValues.put("ISACTIVE", 1);
            contentValues.put("ISDELETE", 0);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", 0);
            contentValues.put("ISSYNC", 0);
            contentValues.put("SID", 0);
            contentValues.put("NOTES", "");

            val = sqldb.update(DBHelper.VISIT_COLLECTION, contentValues," PLAN_VISIT_ID = '" + planId + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }



    */
/**** METHOD TO CHECK IF PLAN_VISIT DATA EXIST OR NOT ****//*


    public Cursor CheckForPlanVisitData(String userId, String custCode, String trgMastId, String isActiveVisit, String isDelete, String entryDate)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PLAN_VISIT WHERE USER_ID = '"+userId+"' AND CUSTOMER_CODE = '"+custCode+"' AND TARGET_MASTER_ID = '"+trgMastId+"' AND IS_ACTIVE_VISIT = '"+isActiveVisit+"' AND ISDELETE = '"+isDelete+"' AND ENTRYDATE = '"+entryDate+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    */
/**** METHOD TO INSERT RECORD IN PLAN_VISIT TABLE ****//*


    public long InsertPlanVisitData(String userId, String trgMastId, String custCode, String visitType, String collectionAmt, String caseType, String ptpDate, String isActiveVisit, String isPlannedVisit, String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("USER_ID", userId);
            contentValues.put("TARGET_MASTER_ID", trgMastId);
            contentValues.put("CUSTOMER_CODE", custCode);
            contentValues.put("VISIT_TYPE_PLANNED", visitType);
            contentValues.put("COLLECTION_PLANNED", collectionAmt);
            contentValues.put("CASE_TYPE", caseType);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("IS_ACTIVE_VISIT", isActiveVisit);
            contentValues.put("IS_PLANNED_VISIT", isPlannedVisit);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);
            
            val = sqldb.insert(DBHelper.PLAN_VISIT, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    */
/**** METHOD TO UPDATE RECORD IN PLAN_VISIT TABLE ****//*


    public long UpdatePlanVisitData(String userId, String trgMastId, String custCode, String visitType, String collectionAmt, String caseType, String ptpDate, String isActiveVisit, String isPlannedVisit, String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("USER_ID", userId);
            contentValues.put("TARGET_MASTER_ID", trgMastId);
            contentValues.put("CUSTOMER_CODE", custCode);
            contentValues.put("VISIT_TYPE_PLANNED", visitType);
            contentValues.put("COLLECTION_PLANNED", collectionAmt);
            contentValues.put("CASE_TYPE", caseType);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("IS_ACTIVE_VISIT", isActiveVisit);
            contentValues.put("IS_PLANNED_VISIT", isPlannedVisit);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);

            val = sqldb.update(DBHelper.PLAN_VISIT, contentValues," USER_ID = '" + userId + "' AND TARGET_MASTER_ID = '" + trgMastId + "' AND CUSTOMER_CODE = '" + custCode + "' AND ISDELETE = 0 ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    */
/**** METHOD TO INSERT RECORD IN PLAN_VISIT_HISTORY TABLE ****//*


    public long InsertPlanVisitHistoryData(String userId, String refId, String trgMastId, String custCode, String visitType, String collectionAmt, String caseType, String ptpDate, String isActiveVisit, String isPlannedVisit, String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("RID", refId);
            contentValues.put("USER_ID", userId);
            contentValues.put("TARGET_MASTER_ID", trgMastId);
            contentValues.put("CUSTOMER_CODE", custCode);
            contentValues.put("VISIT_TYPE_PLANNED", visitType);
            contentValues.put("COLLECTION_PLANNED", collectionAmt);
            contentValues.put("CASE_TYPE", caseType);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("IS_ACTIVE_VISIT", isActiveVisit);
            contentValues.put("IS_PLANNED_VISIT", isPlannedVisit);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);

            val = sqldb.insert(DBHelper.PLAN_VISIT_HISTORY, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    */
/**** METHOD TO SELECT DATA FROM PLAN_VISIT TABLE BASED ON PARAMETERS ****//*


    public Cursor GetPlannedVisitDataByValue(String userId, String custCode, String trgMastId, String isActiveVisit, String isPlanned, String isDelete)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PLAN_VISIT WHERE USER_ID = '"+userId+"' AND CUSTOMER_CODE = '"+custCode+"' AND TARGET_MASTER_ID = '"+trgMastId+"' AND IS_ACTIVE_VISIT = '"+isActiveVisit+"' AND IS_PLANNED_VISIT = '"+isPlanned+"' AND ISDELETE = '"+isDelete+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }



    */
/**** METHOD TO CHECK IF VISIT COLLECTION DATA EXIST OR NOT ****//*


    public Cursor CheckVisitCollectionData(String planVisitId)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM VISIT_COLLECTION WHERE PLAN_VISIT_ID = '"+planVisitId+"' AND ISDELETE IN('0') ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    */
/**** METHOD TO INSERT RECORD IN VISIT_COLLECTION TABLE ****//*


    public long InsertVisitCollectionData(String planVisitId, String visitType, String customerMobile, String collLatitude, String collLongitude, String paymentType, String amount, String chequeNo, String bankName, String ddNo, String micrCode, String ifscCode, String bankAddress,
                                          String transactionDate, String ptpMode, String ptpDate, String ptpTime, String ptpAmount, String fileapproveAmount, String fileDpAmount, String fileLoanAmount, String fileInstNo, String fileInstAmount, String calcLoanAmount,
                                          String calcEmi, String calcTenure, String calcRateOfInterest, String vehicleName, String vehicleNo, String engineNo, String chasisNo, String psName, String psCenter, String seizingAgency, String psFax, String psEmail, String loaneeExist,
                                          String loaneePhoto, String loaneeResiProof, String loaneeIdProof, String loaneeSecCheque, String loaneeResiLightBil, String loaneeResiTaxBill, String loaneeResiAdharCard, String loaneeResiOther, String loaneeResiOtherName,
                                          String loaneeIdPanCard, String loaneeIdElectionCard, String loaneeIdAdharCard, String loaneeIdDrivingLicense, String loaneeIdOther, String loaneeIdOtherName, String coApp1Exist, String coApp1Photo, String coApp1ResiProof,
                                          String coApp1IdProof, String coApp1SecCheque, String coApp1ResiLightBil, String coApp1ResiTaxBill, String coApp1ResiAdharCard, String coApp1ResiOther, String coApp1ResiOtherName, String coApp1IdPanCard, String coApp1IdElectionCard,
                                          String coApp1IdAdharCard, String coApp1IdDrivingLicense, String coApp1IdOther, String coApp1IdOtherName, String coApp2Exist, String coApp2Photo, String coApp2ResiProof, String coApp2IdProof, String coApp2SecCheque, String coApp2ResiLightBil,
                                          String coApp2ResiTaxBill, String coApp2ResiAdharCard, String coApp2ResiOther, String coApp2ResiOtherName, String coApp2IdPanCard, String coApp2IdElectionCard, String coApp2IdAdharCard, String coApp2IdDrivingLicense, String coApp2IdOther, String coApp2IdOtherName,
                                          String priorInfoPolice, String postInfoPolice, String vehiclePhoto1, String vehiclePhoto2, String vehiclePhoto3, String remarkId, String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("PLAN_VISIT_ID", planVisitId);
            contentValues.put("VISIT_TYPE_ACTUAL", visitType);
            contentValues.put("CUSTOMER_MOBILE", customerMobile);
            contentValues.put("COLLECTION_LATITUDE", collLatitude);
            contentValues.put("COLLECTION_LONGITUDE", collLongitude);
            contentValues.put("PAYMENT_TYPE", paymentType);
            contentValues.put("AMOUNT", amount);
            contentValues.put("CHEQUENO", chequeNo);
            contentValues.put("BANKNAME", bankName);
            contentValues.put("DDNO", ddNo);
            contentValues.put("MICRCODE", micrCode);
            contentValues.put("IFSCCODE", ifscCode);
            contentValues.put("BANKADDRESS", bankAddress);
            contentValues.put("TRANSACTION_DATE", transactionDate);
            contentValues.put("PTP_MODE", ptpMode);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("PTP_TIME", ptpTime);
            contentValues.put("PTP_AMT", ptpAmount);
            contentValues.put("FILE_APPROVAL_AMT", fileapproveAmount);
            contentValues.put("DOWN_PAYMENT_AMT", fileDpAmount);
            contentValues.put("FILE_LOAN_AMT", fileLoanAmount);
            contentValues.put("FILE_INST_NO", fileInstNo);
            contentValues.put("FILE_INST_AMT", fileInstAmount);
            contentValues.put("LOAN_AMT", calcLoanAmount);
            contentValues.put("EMI", calcEmi);
            contentValues.put("TENURE", calcTenure);
            contentValues.put("RATE_OF_INTEREST", calcRateOfInterest);
            contentValues.put("VEHICLE_NAME", vehicleName);
            contentValues.put("VEHICLE_NO", vehicleNo);
            contentValues.put("ENGINE_NO", engineNo);
            contentValues.put("CHASIS_NO", chasisNo);
            contentValues.put("NAME_OF_POLICE_STATION", psName);
            contentValues.put("CENTER_OF_POLICE_STATION", psCenter);
            contentValues.put("SEIZING_AGENCY_NAME", seizingAgency);
            contentValues.put("FAX_NO_POLICE_STATION", psFax);
            contentValues.put("EMAIL_POLICE_STATION", psEmail);
            contentValues.put("LOANEE_EXIST", loaneeExist);
            contentValues.put("LOANEE_PHOTO", loaneePhoto);
            contentValues.put("LOANEE_RESI_PROOF", loaneeResiProof);
            contentValues.put("LOANEE_ID_PROOF", loaneeIdProof);
            contentValues.put("LOANEE_SECURITY_CHEQUE", loaneeSecCheque);
            contentValues.put("LOANEE_RESI_PROOF_LIGHT_BILL", loaneeResiLightBil);
            contentValues.put("LOANEE_RESI_PROOF_TAX_BILL", loaneeResiTaxBill);
            contentValues.put("LOANEE_RESI_PROOF_AADHAR_CARD", loaneeResiAdharCard);
            contentValues.put("LOANEE_RESI_PROOF_OTHER", loaneeResiOther);
            contentValues.put("LOANEE_RESI_PROOF_OTHER_NAME", loaneeResiOtherName);
            contentValues.put("LOANEE_ID_PROOF_PAN_CARD", loaneeIdPanCard);
            contentValues.put("LOANEE_ID_PROOF_ELECTION_CARD", loaneeIdElectionCard);
            contentValues.put("LOANEE_ID_PROOF_AADHAR_CARD", loaneeIdAdharCard);
            contentValues.put("LOANEE_ID_PROOF_DRIVING_LICENSE", loaneeIdDrivingLicense);
            contentValues.put("LOANEE_ID_PROOF_OTHER", loaneeIdOther);
            contentValues.put("LOANEE_ID_PROOF_OTHER_NAME", loaneeIdOtherName);
            contentValues.put("COAPP1_EXIST", coApp1Exist);
            contentValues.put("COAPP1_PHOTO", coApp1Photo);
            contentValues.put("COAPP1_RESI_PROOF", coApp1ResiProof);
            contentValues.put("COAPP1_ID_PROOF", coApp1IdProof);
            contentValues.put("COAPP1_SECURITY_CHEQUE", coApp1SecCheque);
            contentValues.put("COAPP1_RESI_PROOF_LIGHT_BILL", coApp1ResiLightBil);
            contentValues.put("COAPP1_RESI_PROOF_TAX_BILL", coApp1ResiTaxBill);
            contentValues.put("COAPP1_RESI_PROOF_AADHAR_CARD", coApp1ResiAdharCard);
            contentValues.put("COAPP1_RESI_PROOF_OTHER", coApp1ResiOther);
            contentValues.put("COAPP1_RESI_PROOF_OTHER_NAME", coApp1ResiOtherName);
            contentValues.put("COAPP1_ID_PROOF_PAN_CARD", coApp1IdPanCard);
            contentValues.put("COAPP1_ID_PROOF_ELECTION_CARD", coApp1IdElectionCard);
            contentValues.put("COAPP1_ID_PROOF_AADHAR_CARD", coApp1IdAdharCard);
            contentValues.put("COAPP1_ID_PROOF_DRIVING_LICENSE", coApp1IdDrivingLicense);
            contentValues.put("COAPP1_ID_PROOF_OTHER", coApp1IdOther);
            contentValues.put("COAPP1_ID_PROOF_OTHER_NAME", coApp1IdOtherName);
            contentValues.put("COAPP2_EXIST", coApp2Exist);
            contentValues.put("COAPP2_PHOTO", coApp2Photo);
            contentValues.put("COAPP2_RESI_PROOF", coApp2ResiProof);
            contentValues.put("COAPP2_ID_PROOF", coApp2IdProof);
            contentValues.put("COAPP2_SECURITY_CHEQUE", coApp2SecCheque);
            contentValues.put("COAPP2_RESI_PROOF_LIGHT_BILL", coApp2ResiLightBil);
            contentValues.put("COAPP2_RESI_PROOF_TAX_BILL", coApp2ResiTaxBill);
            contentValues.put("COAPP2_RESI_PROOF_AADHAR_CARD", coApp2ResiAdharCard);
            contentValues.put("COAPP2_RESI_PROOF_OTHER", coApp2ResiOther);
            contentValues.put("COAPP2_RESI_PROOF_OTHER_NAME", coApp2ResiOtherName);
            contentValues.put("COAPP2_ID_PROOF_PAN_CARD", coApp2IdPanCard);
            contentValues.put("COAPP2_ID_PROOF_ELECTION_CARD", coApp2IdElectionCard);
            contentValues.put("COAPP2_ID_PROOF_AADHAR_CARD", coApp2IdAdharCard);
            contentValues.put("COAPP2_ID_PROOF_DRIVING_LICENSE", coApp2IdDrivingLicense);
            contentValues.put("COAPP2_ID_PROOF_OTHER", coApp2IdOther);
            contentValues.put("COAPP2_ID_PROOF_OTHER_NAME", coApp2IdOtherName);
            contentValues.put("PRIOR_INFO_TO_POLICE", priorInfoPolice);
            contentValues.put("POST_INFO_TO_POLICE", postInfoPolice);
            contentValues.put("VEHICLE_PHOTO1", vehiclePhoto1);
            contentValues.put("VEHICLE_PHOTO2", vehiclePhoto2);
            contentValues.put("VEHICLE_PHOTO3", vehiclePhoto3);
            contentValues.put("REMARKID", remarkId);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);
            
            val = sqldb.insert(DBHelper.VISIT_COLLECTION, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    */
/**** METHOD TO UPDATE RECORD IN VISIT_COLLECTION TABLE ****//*


    public long UpdateVisitCollectionData(String planVisitId, String visitType, String customerMobile, String collLatitude, String collLongitude, String paymentType, String amount, String chequeNo, String bankName, String ddNo, String micrCode, String ifscCode, String bankAddress,
                                          String transactionDate, String ptpMode, String ptpDate, String ptpTime, String ptpAmount, String fileapproveAmount, String fileDpAmount, String fileLoanAmount, String fileInstNo, String fileInstAmount, String calcLoanAmount,
                                          String calcEmi, String calcTenure, String calcRateOfInterest, String vehicleName, String vehicleNo, String engineNo, String chasisNo, String psName, String psCenter, String seizingAgency, String psFax, String psEmail, String loaneeExist,
                                          String loaneePhoto, String loaneeResiProof, String loaneeIdProof, String loaneeSecCheque, String loaneeResiLightBil, String loaneeResiTaxBill, String loaneeResiAdharCard, String loaneeResiOther, String loaneeResiOtherName,
                                          String loaneeIdPanCard, String loaneeIdElectionCard, String loaneeIdAdharCard, String loaneeIdDrivingLicense, String loaneeIdOther, String loaneeIdOtherName, String coApp1Exist, String coApp1Photo, String coApp1ResiProof,
                                          String coApp1IdProof, String coApp1SecCheque, String coApp1ResiLightBil, String coApp1ResiTaxBill, String coApp1ResiAdharCard, String coApp1ResiOther, String coApp1ResiOtherName, String coApp1IdPanCard, String coApp1IdElectionCard,
                                          String coApp1IdAdharCard, String coApp1IdDrivingLicense, String coApp1IdOther, String coApp1IdOtherName, String coApp2Exist, String coApp2Photo, String coApp2ResiProof, String coApp2IdProof, String coApp2SecCheque, String coApp2ResiLightBil,
                                          String coApp2ResiTaxBill, String coApp2ResiAdharCard, String coApp2ResiOther, String coApp2ResiOtherName, String coApp2IdPanCard, String coApp2IdElectionCard, String coApp2IdAdharCard, String coApp2IdDrivingLicense, String coApp2IdOther, String coApp2IdOtherName,
                                          String priorInfoPolice, String postInfoPolice, String vehiclePhoto1, String vehiclePhoto2, String vehiclePhoto3, String remarkId, String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("PLAN_VISIT_ID", planVisitId);
            contentValues.put("VISIT_TYPE_ACTUAL", visitType);
            contentValues.put("CUSTOMER_MOBILE", customerMobile);
            contentValues.put("COLLECTION_LATITUDE", collLatitude);
            contentValues.put("COLLECTION_LONGITUDE", collLongitude);
            contentValues.put("PAYMENT_TYPE", paymentType);
            contentValues.put("AMOUNT", amount);
            contentValues.put("CHEQUENO", chequeNo);
            contentValues.put("BANKNAME", bankName);
            contentValues.put("DDNO", ddNo);
            contentValues.put("MICRCODE", micrCode);
            contentValues.put("IFSCCODE", ifscCode);
            contentValues.put("BANKADDRESS", bankAddress);
            contentValues.put("TRANSACTION_DATE", transactionDate);
            contentValues.put("PTP_MODE", ptpMode);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("PTP_TIME", ptpTime);
            contentValues.put("PTP_AMT", ptpAmount);
            contentValues.put("FILE_APPROVAL_AMT", fileapproveAmount);
            contentValues.put("DOWN_PAYMENT_AMT", fileDpAmount);
            contentValues.put("FILE_LOAN_AMT", fileLoanAmount);
            contentValues.put("FILE_INST_NO", fileInstNo);
            contentValues.put("FILE_INST_AMT", fileInstAmount);
            contentValues.put("LOAN_AMT", calcLoanAmount);
            contentValues.put("EMI", calcEmi);
            contentValues.put("TENURE", calcTenure);
            contentValues.put("RATE_OF_INTEREST", calcRateOfInterest);
            contentValues.put("VEHICLE_NAME", vehicleName);
            contentValues.put("VEHICLE_NO", vehicleNo);
            contentValues.put("ENGINE_NO", engineNo);
            contentValues.put("CHASIS_NO", chasisNo);
            contentValues.put("NAME_OF_POLICE_STATION", psName);
            contentValues.put("CENTER_OF_POLICE_STATION", psCenter);
            contentValues.put("SEIZING_AGENCY_NAME", seizingAgency);
            contentValues.put("FAX_NO_POLICE_STATION", psFax);
            contentValues.put("EMAIL_POLICE_STATION", psEmail);
            contentValues.put("LOANEE_EXIST", loaneeExist);
            contentValues.put("LOANEE_PHOTO", loaneePhoto);
            contentValues.put("LOANEE_RESI_PROOF", loaneeResiProof);
            contentValues.put("LOANEE_ID_PROOF", loaneeIdProof);
            contentValues.put("LOANEE_SECURITY_CHEQUE", loaneeSecCheque);
            contentValues.put("LOANEE_RESI_PROOF_LIGHT_BILL", loaneeResiLightBil);
            contentValues.put("LOANEE_RESI_PROOF_TAX_BILL", loaneeResiTaxBill);
            contentValues.put("LOANEE_RESI_PROOF_AADHAR_CARD", loaneeResiAdharCard);
            contentValues.put("LOANEE_RESI_PROOF_OTHER", loaneeResiOther);
            contentValues.put("LOANEE_RESI_PROOF_OTHER_NAME", loaneeResiOtherName);
            contentValues.put("LOANEE_ID_PROOF_PAN_CARD", loaneeIdPanCard);
            contentValues.put("LOANEE_ID_PROOF_ELECTION_CARD", loaneeIdElectionCard);
            contentValues.put("LOANEE_ID_PROOF_AADHAR_CARD", loaneeIdAdharCard);
            contentValues.put("LOANEE_ID_PROOF_DRIVING_LICENSE", loaneeIdDrivingLicense);
            contentValues.put("LOANEE_ID_PROOF_OTHER", loaneeIdOther);
            contentValues.put("LOANEE_ID_PROOF_OTHER_NAME", loaneeIdOtherName);
            contentValues.put("COAPP1_EXIST", coApp1Exist);
            contentValues.put("COAPP1_PHOTO", coApp1Photo);
            contentValues.put("COAPP1_RESI_PROOF", coApp1ResiProof);
            contentValues.put("COAPP1_ID_PROOF", coApp1IdProof);
            contentValues.put("COAPP1_SECURITY_CHEQUE", coApp1SecCheque);
            contentValues.put("COAPP1_RESI_PROOF_LIGHT_BILL", coApp1ResiLightBil);
            contentValues.put("COAPP1_RESI_PROOF_TAX_BILL", coApp1ResiTaxBill);
            contentValues.put("COAPP1_RESI_PROOF_AADHAR_CARD", coApp1ResiAdharCard);
            contentValues.put("COAPP1_RESI_PROOF_OTHER", coApp1ResiOther);
            contentValues.put("COAPP1_RESI_PROOF_OTHER_NAME", coApp1ResiOtherName);
            contentValues.put("COAPP1_ID_PROOF_PAN_CARD", coApp1IdPanCard);
            contentValues.put("COAPP1_ID_PROOF_ELECTION_CARD", coApp1IdElectionCard);
            contentValues.put("COAPP1_ID_PROOF_AADHAR_CARD", coApp1IdAdharCard);
            contentValues.put("COAPP1_ID_PROOF_DRIVING_LICENSE", coApp1IdDrivingLicense);
            contentValues.put("COAPP1_ID_PROOF_OTHER", coApp1IdOther);
            contentValues.put("COAPP1_ID_PROOF_OTHER_NAME", coApp1IdOtherName);
            contentValues.put("COAPP2_EXIST", coApp2Exist);
            contentValues.put("COAPP2_PHOTO", coApp2Photo);
            contentValues.put("COAPP2_RESI_PROOF", coApp2ResiProof);
            contentValues.put("COAPP2_ID_PROOF", coApp2IdProof);
            contentValues.put("COAPP2_SECURITY_CHEQUE", coApp2SecCheque);
            contentValues.put("COAPP2_RESI_PROOF_LIGHT_BILL", coApp2ResiLightBil);
            contentValues.put("COAPP2_RESI_PROOF_TAX_BILL", coApp2ResiTaxBill);
            contentValues.put("COAPP2_RESI_PROOF_AADHAR_CARD", coApp2ResiAdharCard);
            contentValues.put("COAPP2_RESI_PROOF_OTHER", coApp2ResiOther);
            contentValues.put("COAPP2_RESI_PROOF_OTHER_NAME", coApp2ResiOtherName);
            contentValues.put("COAPP2_ID_PROOF_PAN_CARD", coApp2IdPanCard);
            contentValues.put("COAPP2_ID_PROOF_ELECTION_CARD", coApp2IdElectionCard);
            contentValues.put("COAPP2_ID_PROOF_AADHAR_CARD", coApp2IdAdharCard);
            contentValues.put("COAPP2_ID_PROOF_DRIVING_LICENSE", coApp2IdDrivingLicense);
            contentValues.put("COAPP2_ID_PROOF_OTHER", coApp2IdOther);
            contentValues.put("COAPP2_ID_PROOF_OTHER_NAME", coApp2IdOtherName);
            contentValues.put("PRIOR_INFO_TO_POLICE", priorInfoPolice);
            contentValues.put("POST_INFO_TO_POLICE", postInfoPolice);
            contentValues.put("VEHICLE_PHOTO1", vehiclePhoto1);
            contentValues.put("VEHICLE_PHOTO2", vehiclePhoto2);
            contentValues.put("VEHICLE_PHOTO3", vehiclePhoto3);
            contentValues.put("REMARKID", remarkId);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);

            val = sqldb.update(DBHelper.VISIT_COLLECTION, contentValues," PLAN_VISIT_ID = '" + planVisitId + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    */
/**** METHOD TO INSERT RECORD IN VISIT_COLLECTION_HISTORY TABLE ****//*


    public long InsertVisitCollectionHistoryData(String refId, String planVisitId, String visitType, String customerMobile, String collLatitude, String collLongitude, String paymentType, String amount, String chequeNo, String bankName, String ddNo, String micrCode, String ifscCode, String bankAddress,
                                          String transactionDate, String ptpMode, String ptpDate, String ptpTime, String ptpAmount, String fileapproveAmount, String fileDpAmount, String fileLoanAmount, String fileInstNo, String fileInstAmount, String calcLoanAmount,
                                          String calcEmi, String calcTenure, String calcRateOfInterest, String vehicleName, String vehicleNo, String engineNo, String chasisNo, String psName, String psCenter, String seizingAgency, String psFax, String psEmail, String loaneeExist,
                                          String loaneePhoto, String loaneeResiProof, String loaneeIdProof, String loaneeSecCheque, String loaneeResiLightBil, String loaneeResiTaxBill, String loaneeResiAdharCard, String loaneeResiOther, String loaneeResiOtherName,
                                          String loaneeIdPanCard, String loaneeIdElectionCard, String loaneeIdAdharCard, String loaneeIdDrivingLicense, String loaneeIdOther, String loaneeIdOtherName, String coApp1Exist, String coApp1Photo, String coApp1ResiProof,
                                          String coApp1IdProof, String coApp1SecCheque, String coApp1ResiLightBil, String coApp1ResiTaxBill, String coApp1ResiAdharCard, String coApp1ResiOther, String coApp1ResiOtherName, String coApp1IdPanCard, String coApp1IdElectionCard,
                                          String coApp1IdAdharCard, String coApp1IdDrivingLicense, String coApp1IdOther, String coApp1IdOtherName, String coApp2Exist, String coApp2Photo, String coApp2ResiProof, String coApp2IdProof, String coApp2SecCheque, String coApp2ResiLightBil,
                                          String coApp2ResiTaxBill, String coApp2ResiAdharCard, String coApp2ResiOther, String coApp2ResiOtherName, String coApp2IdPanCard, String coApp2IdElectionCard, String coApp2IdAdharCard, String coApp2IdDrivingLicense, String coApp2IdOther, String coApp2IdOtherName,
                                          String priorInfoPolice, String postInfoPolice, String vehiclePhoto1, String vehiclePhoto2, String vehiclePhoto3, String remarkId, String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("RID", refId);
            contentValues.put("PLAN_VISIT_ID", planVisitId);
            contentValues.put("VISIT_TYPE_ACTUAL", visitType);
            contentValues.put("CUSTOMER_MOBILE", customerMobile);
            contentValues.put("COLLECTION_LATITUDE", collLatitude);
            contentValues.put("COLLECTION_LONGITUDE", collLongitude);
            contentValues.put("PAYMENT_TYPE", paymentType);
            contentValues.put("AMOUNT", amount);
            contentValues.put("CHEQUENO", chequeNo);
            contentValues.put("BANKNAME", bankName);
            contentValues.put("DDNO", ddNo);
            contentValues.put("MICRCODE", micrCode);
            contentValues.put("IFSCCODE", ifscCode);
            contentValues.put("BANKADDRESS", bankAddress);
            contentValues.put("TRANSACTION_DATE", transactionDate);
            contentValues.put("PTP_MODE", ptpMode);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("PTP_TIME", ptpTime);
            contentValues.put("PTP_AMT", ptpAmount);
            contentValues.put("FILE_APPROVAL_AMT", fileapproveAmount);
            contentValues.put("DOWN_PAYMENT_AMT", fileDpAmount);
            contentValues.put("FILE_LOAN_AMT", fileLoanAmount);
            contentValues.put("FILE_INST_NO", fileInstNo);
            contentValues.put("FILE_INST_AMT", fileInstAmount);
            contentValues.put("LOAN_AMT", calcLoanAmount);
            contentValues.put("EMI", calcEmi);
            contentValues.put("TENURE", calcTenure);
            contentValues.put("RATE_OF_INTEREST", calcRateOfInterest);
            contentValues.put("VEHICLE_NAME", vehicleName);
            contentValues.put("VEHICLE_NO", vehicleNo);
            contentValues.put("ENGINE_NO", engineNo);
            contentValues.put("CHASIS_NO", chasisNo);
            contentValues.put("NAME_OF_POLICE_STATION", psName);
            contentValues.put("CENTER_OF_POLICE_STATION", psCenter);
            contentValues.put("SEIZING_AGENCY_NAME", seizingAgency);
            contentValues.put("FAX_NO_POLICE_STATION", psFax);
            contentValues.put("EMAIL_POLICE_STATION", psEmail);
            contentValues.put("LOANEE_EXIST", loaneeExist);
            contentValues.put("LOANEE_PHOTO", loaneePhoto);
            contentValues.put("LOANEE_RESI_PROOF", loaneeResiProof);
            contentValues.put("LOANEE_ID_PROOF", loaneeIdProof);
            contentValues.put("LOANEE_SECURITY_CHEQUE", loaneeSecCheque);
            contentValues.put("LOANEE_RESI_PROOF_LIGHT_BILL", loaneeResiLightBil);
            contentValues.put("LOANEE_RESI_PROOF_TAX_BILL", loaneeResiTaxBill);
            contentValues.put("LOANEE_RESI_PROOF_AADHAR_CARD", loaneeResiAdharCard);
            contentValues.put("LOANEE_RESI_PROOF_OTHER", loaneeResiOther);
            contentValues.put("LOANEE_RESI_PROOF_OTHER_NAME", loaneeResiOtherName);
            contentValues.put("LOANEE_ID_PROOF_PAN_CARD", loaneeIdPanCard);
            contentValues.put("LOANEE_ID_PROOF_ELECTION_CARD", loaneeIdElectionCard);
            contentValues.put("LOANEE_ID_PROOF_AADHAR_CARD", loaneeIdAdharCard);
            contentValues.put("LOANEE_ID_PROOF_DRIVING_LICENSE", loaneeIdDrivingLicense);
            contentValues.put("LOANEE_ID_PROOF_OTHER", loaneeIdOther);
            contentValues.put("LOANEE_ID_PROOF_OTHER_NAME", loaneeIdOtherName);
            contentValues.put("COAPP1_EXIST", coApp1Exist);
            contentValues.put("COAPP1_PHOTO", coApp1Photo);
            contentValues.put("COAPP1_RESI_PROOF", coApp1ResiProof);
            contentValues.put("COAPP1_ID_PROOF", coApp1IdProof);
            contentValues.put("COAPP1_SECURITY_CHEQUE", coApp1SecCheque);
            contentValues.put("COAPP1_RESI_PROOF_LIGHT_BILL", coApp1ResiLightBil);
            contentValues.put("COAPP1_RESI_PROOF_TAX_BILL", coApp1ResiTaxBill);
            contentValues.put("COAPP1_RESI_PROOF_AADHAR_CARD", coApp1ResiAdharCard);
            contentValues.put("COAPP1_RESI_PROOF_OTHER", coApp1ResiOther);
            contentValues.put("COAPP1_RESI_PROOF_OTHER_NAME", coApp1ResiOtherName);
            contentValues.put("COAPP1_ID_PROOF_PAN_CARD", coApp1IdPanCard);
            contentValues.put("COAPP1_ID_PROOF_ELECTION_CARD", coApp1IdElectionCard);
            contentValues.put("COAPP1_ID_PROOF_AADHAR_CARD", coApp1IdAdharCard);
            contentValues.put("COAPP1_ID_PROOF_DRIVING_LICENSE", coApp1IdDrivingLicense);
            contentValues.put("COAPP1_ID_PROOF_OTHER", coApp1IdOther);
            contentValues.put("COAPP1_ID_PROOF_OTHER_NAME", coApp1IdOtherName);
            contentValues.put("COAPP2_EXIST", coApp2Exist);
            contentValues.put("COAPP2_PHOTO", coApp2Photo);
            contentValues.put("COAPP2_RESI_PROOF", coApp2ResiProof);
            contentValues.put("COAPP2_ID_PROOF", coApp2IdProof);
            contentValues.put("COAPP2_SECURITY_CHEQUE", coApp2SecCheque);
            contentValues.put("COAPP2_RESI_PROOF_LIGHT_BILL", coApp2ResiLightBil);
            contentValues.put("COAPP2_RESI_PROOF_TAX_BILL", coApp2ResiTaxBill);
            contentValues.put("COAPP2_RESI_PROOF_AADHAR_CARD", coApp2ResiAdharCard);
            contentValues.put("COAPP2_RESI_PROOF_OTHER", coApp2ResiOther);
            contentValues.put("COAPP2_RESI_PROOF_OTHER_NAME", coApp2ResiOtherName);
            contentValues.put("COAPP2_ID_PROOF_PAN_CARD", coApp2IdPanCard);
            contentValues.put("COAPP2_ID_PROOF_ELECTION_CARD", coApp2IdElectionCard);
            contentValues.put("COAPP2_ID_PROOF_AADHAR_CARD", coApp2IdAdharCard);
            contentValues.put("COAPP2_ID_PROOF_DRIVING_LICENSE", coApp2IdDrivingLicense);
            contentValues.put("COAPP2_ID_PROOF_OTHER", coApp2IdOther);
            contentValues.put("COAPP2_ID_PROOF_OTHER_NAME", coApp2IdOtherName);
            contentValues.put("PRIOR_INFO_TO_POLICE", priorInfoPolice);
            contentValues.put("POST_INFO_TO_POLICE", postInfoPolice);
            contentValues.put("VEHICLE_PHOTO1", vehiclePhoto1);
            contentValues.put("VEHICLE_PHOTO2", vehiclePhoto2);
            contentValues.put("VEHICLE_PHOTO3", vehiclePhoto3);
            contentValues.put("REMARKID", remarkId);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);

            val = sqldb.insert(DBHelper.VISIT_COLLECTION, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in CUSTOMER_MASTER

    public Cursor checkForDuplicateCustomerMasterData(String custCode)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM CUSTMS WHERE CUST_CODE = '"+custCode+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert CUSTOMER_MASTER Data in SQLite Database

    public long insertCustomerMasterData(String cBookSrNo, String cCenter, String cSourceBy, String cSalesName, String cJlgGroup, String cSchCode, String cScrCode, String cSegment, String cBirthDate, String cEnrlDate, String cInstDate, String cCustCode, String cCustName, String cRBlockNo, String cRAdd1, String cRAdd2, String cRAdd3, String cRCity, String cRPin, String cRPhone, String cMPhone, String cOName, String cLclass, String cNatBus,
                                         String cO_Dept, String cO_Dsgn, String cO_Gross, String cO_Net, String cO_Add1, String cO_Add2, String cO_Add3, String cO_City, String cO_Pin, String cO_Phone, String cOwnHouse, String cReference, String cSpecial, String cSaleRate, String cLoanAmount, String cFc, String cSaleCR, String cPassCR, String cAccessCR, String cDHpaCR, String cRtoCR, String cMfCR, String cMiscCR, String cServCR, String cDocCR, String cObcCR, String cSiteVCR,
                                         String cSpdcCR, String cStaxCR, String cInsuCR, String cMontInt, String cDpayAmt, String cI_CQ, String cI_DT, String cI_QAmount, String cI_CAmount, String cInstNos, String cInstAmount, String cEqu, String cInstInt, String cInst1_Nos, String cInst1_Amt, String cInst2_Nos, String cInst2_Amt, String cInst3_Nos, String cInst3_Amt, String cInst4_Nos, String cInst4_Amt, String cInst5_Nos, String cInst5_Amt, String cInst6_Nos, String cInst6_Amt,
                                         String cRfndAmt, String cRoi, String cSdAdj, String cSdAmt, String cSdIntAmt, String cSdRfnd, String cSuplCode, String cSuplMore, String cAmount, String cItemCode, String cCashChq, String cNoChqs, String cEmiAmt, String cDocAmt, String cServAmt, String cSaleAmt, String cDHpaAmt, String cOtherAmt, String cEmi, String cDealCost, String cHpaAmt, String cTenure, String cCanFlag, String cChqsRecvd, String cMode, String cMarkIrr, String cRtoAcc, String cTDiscount, String cDeHours,
                                         String cToday, String cHours, String cVerifiedBy, String cRemark, String cSvWhom, String cDisbursed, String cMgch, String cOld, String cClosed, String cShName, String cVehNo, String cPaper, String cChqNo, String cBkCode, String cBranch, String cPurpose, String cCenRAdd, String cOCustCode, String cDacCode, String custName1, String itemName, String tranchNo, String srfndAmt)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("BOOK_SR_NO", cBookSrNo);
            contentValues.put("CENTRE", cCenter);
            contentValues.put("SOURCEBY", cSourceBy);
            contentValues.put("SALES_NAME", cSalesName);
            contentValues.put("JLG_GROUP", cJlgGroup);
            contentValues.put("SCH_CODE", cSchCode);
            contentValues.put("SCR_CODE", cScrCode);
            contentValues.put("SEGMENT", cSegment);
            contentValues.put("BIRTH_DT", cBirthDate);
            contentValues.put("ENRL_DT", cEnrlDate);
            contentValues.put("INST_DT", cInstDate);
            contentValues.put("CUST_CODE", cCustCode);
            contentValues.put("CUST_NAME", cCustName);
            contentValues.put("R_BLK_NO", cRBlockNo);
            contentValues.put("R_ADD1", cRAdd1);
            contentValues.put("R_ADD2", cRAdd2);
            contentValues.put("R_ADD3", cRAdd3);
            contentValues.put("R_CITY", cRCity);
            contentValues.put("R_PIN", cRPin);
            contentValues.put("R_PHONE", cRPhone);
            contentValues.put("M_PHONE", cMPhone);
            contentValues.put("O_NAME", cOName);
            contentValues.put("L_CLASS", cLclass);
            contentValues.put("NAT_BUS", cNatBus);
            contentValues.put("O_DEPT", cO_Dept);
            contentValues.put("O_DSGN", cO_Dsgn);
            contentValues.put("O_GROSS", cO_Gross);
            contentValues.put("O_NET", cO_Net);
            contentValues.put("O_ADD1", cO_Add1);
            contentValues.put("O_ADD2", cO_Add2);
            contentValues.put("O_ADD3", cO_Add3);
            contentValues.put("O_CITY", cO_City);
            contentValues.put("O_PIN", cO_Pin);
            contentValues.put("O_PHONE", cO_Phone);
            contentValues.put("OWN_HOUSE", cOwnHouse);
            contentValues.put("REFERENCE", cReference);
            contentValues.put("SPECIAL", cSpecial);
            contentValues.put("SALE_RATE", cSaleRate);
            contentValues.put("LOAN_AMT", cLoanAmount);
            contentValues.put("FC", cFc);
            contentValues.put("SALE_CR", cSaleCR);
            contentValues.put("PASS_CR", cPassCR);
            contentValues.put("ACCESS_CR", cAccessCR);
            contentValues.put("D_HPA_CR", cDHpaCR);
            contentValues.put("RTO_CR", cRtoCR);
            contentValues.put("MF_CR", cMfCR);
            contentValues.put("MISC_CR", cMiscCR);
            contentValues.put("SERV_CR", cServCR);
            contentValues.put("DOC_CR", cDocCR);
            contentValues.put("OBC_CR", cObcCR);
            contentValues.put("SITEV_CR", cSiteVCR);
            contentValues.put("SPDC_CR", cSpdcCR);
            contentValues.put("STAX_CR", cStaxCR);
            contentValues.put("INSU_CR", cInsuCR);
            contentValues.put("mont_int", cMontInt);
            contentValues.put("DPAY_AMT", cDpayAmt);
            contentValues.put("I_CQ", cI_CQ);
            contentValues.put("I_DT", cI_DT);
            contentValues.put("I_QAMOUNT", cI_QAmount);
            contentValues.put("I_CAMOUNT", cI_CAmount);
            contentValues.put("INST_NOS", cInstNos);
            contentValues.put("INST_AMT", cInstAmount);
            contentValues.put("EQU", cEqu);
            contentValues.put("INST_INT", cInstInt);
            contentValues.put("INST1_NOS", cInst1_Nos);
            contentValues.put("INST1_AMT", cInst1_Amt);
            contentValues.put("INST2_NOS", cInst2_Nos);
            contentValues.put("INST2_AMT", cInst2_Amt);
            contentValues.put("INST3_NOS", cInst3_Nos);
            contentValues.put("INST3_AMT", cInst3_Amt);
            contentValues.put("INST4_NOS", cInst4_Nos);
            contentValues.put("INST4_AMT", cInst4_Amt);
            contentValues.put("INST5_NOS", cInst5_Nos);
            contentValues.put("INST5_AMT", cInst5_Amt);
            contentValues.put("INST6_NOS", cInst6_Nos);
            contentValues.put("INST6_AMT", cInst6_Amt);
            contentValues.put("RFND_AMT", cRfndAmt);
            contentValues.put("ROI", cRoi);
            contentValues.put("SD_ADJ", cSdAdj);
            contentValues.put("SD_AMT", cSdAmt);
            contentValues.put("SD_INT_AMT", cSdIntAmt);
            contentValues.put("SD_RFND", cSdRfnd);
            contentValues.put("SUPL_CODE", cSuplCode);
            contentValues.put("SUPL_MORE", cSuplMore);
            contentValues.put("AMOUNT", cAmount);
            contentValues.put("ITEM_CODE", cItemCode);
            contentValues.put("CASH_CHQ", cCashChq);
            contentValues.put("NO_CHQS", cNoChqs);
            contentValues.put("EMI_AMT", cEmiAmt);
            contentValues.put("DOC_AMT", cDocAmt);
            contentValues.put("SERV_AMT", cServAmt);
            contentValues.put("SALE_AMT", cSaleAmt);
            contentValues.put("D_HPA_AMT", cDHpaAmt);
            contentValues.put("OTHER_AMT", cOtherAmt);
            contentValues.put("EMI", cEmi);
            contentValues.put("DEAL_COST", cDealCost);
            contentValues.put("HPA_AMT", cHpaAmt);
            contentValues.put("TENURE", cTenure);
            contentValues.put("CAN_FLAG", cCanFlag);
            contentValues.put("CHQS_RECD", cChqsRecvd);
            contentValues.put("MODE", cMode);
            contentValues.put("MARK_IRR", cMarkIrr);
            contentValues.put("RTO_ACC", cRtoAcc);
            contentValues.put("TDISCNT", cTDiscount);
            contentValues.put("DE_HOURS", cDeHours);
            contentValues.put("TODAY", cToday);
            contentValues.put("HOURS", cHours);
            contentValues.put("VERIFIEDBY", cVerifiedBy);
            contentValues.put("REMARK", cRemark);
            contentValues.put("SV_WHOM", cSvWhom);
            contentValues.put("DISBURSED", cDisbursed);
            contentValues.put("MGCH", cMgch);
            contentValues.put("OLD", cOld);
            contentValues.put("CLOSED", cClosed);
            contentValues.put("SH_NAME", cShName);
            contentValues.put("VEH_NO", cVehNo);
            contentValues.put("PAPER", cPaper);
            contentValues.put("C_CHQ_NO", cChqNo);
            contentValues.put("CBK_CODE", cBkCode);
            contentValues.put("C_BRANCH", cBranch);
            contentValues.put("PURPOSE", cPurpose);
            contentValues.put("CEN_RADD", cCenRAdd);
            contentValues.put("OCUST_CODE", cOCustCode);
            contentValues.put("DAC_CODE", cDacCode);
            contentValues.put("CUST_NAME1", custName1);
            contentValues.put("ITEM_NAME", itemName);
            contentValues.put("TRANCH_NO", tranchNo);
            contentValues.put("SRFND_AMT", srfndAmt);

            val = sqldb.insert(DBHelper.CUSTMS, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to insert CUSTOMER_MASTER Data in SQLite Database

    public long updateCustomerMasterData(String cBookSrNo, String cCenter, String cSourceBy, String cSalesName, String cJlgGroup, String cSchCode, String cScrCode, String cSegment, String cBirthDate, String cEnrlDate, String cInstDate, String cCustCode, String cCustName, String cRBlockNo, String cRAdd1, String cRAdd2, String cRAdd3, String cRCity, String cRPin, String cRPhone, String cMPhone, String cOName, String cLclass, String cNatBus,
                                         String cO_Dept, String cO_Dsgn, String cO_Gross, String cO_Net, String cO_Add1, String cO_Add2, String cO_Add3, String cO_City, String cO_Pin, String cO_Phone, String cOwnHouse, String cReference, String cSpecial, String cSaleRate, String cLoanAmount, String cFc, String cSaleCR, String cPassCR, String cAccessCR, String cDHpaCR, String cRtoCR, String cMfCR, String cMiscCR, String cServCR, String cDocCR, String cObcCR, String cSiteVCR,
                                         String cSpdcCR, String cStaxCR, String cInsuCR, String cMontInt, String cDpayAmt, String cI_CQ, String cI_DT, String cI_QAmount, String cI_CAmount, String cInstNos, String cInstAmount, String cEqu, String cInstInt, String cInst1_Nos, String cInst1_Amt, String cInst2_Nos, String cInst2_Amt, String cInst3_Nos, String cInst3_Amt, String cInst4_Nos, String cInst4_Amt, String cInst5_Nos, String cInst5_Amt, String cInst6_Nos, String cInst6_Amt,
                                         String cRfndAmt, String cRoi, String cSdAdj, String cSdAmt, String cSdIntAmt, String cSdRfnd, String cSuplCode, String cSuplMore, String cAmount, String cItemCode, String cCashChq, String cNoChqs, String cEmiAmt, String cDocAmt, String cServAmt, String cSaleAmt, String cDHpaAmt, String cOtherAmt, String cEmi, String cDealCost, String cHpaAmt, String cTenure, String cCanFlag, String cChqsRecvd, String cMode, String cMarkIrr, String cRtoAcc, String cTDiscount, String cDeHours,
                                         String cToday, String cHours, String cVerifiedBy, String cRemark, String cSvWhom, String cDisbursed, String cMgch, String cOld, String cClosed, String cShName, String cVehNo, String cPaper, String cChqNo, String cBkCode, String cBranch, String cPurpose, String cCenRAdd, String cOCustCode, String cDacCode, String custName1, String itemName, String tranchNo, String srfndAmt)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("BOOK_SR_NO", cBookSrNo);
            contentValues.put("CENTRE", cCenter);
            contentValues.put("SOURCEBY", cSourceBy);
            contentValues.put("SALES_NAME", cSalesName);
            contentValues.put("JLG_GROUP", cJlgGroup);
            contentValues.put("SCH_CODE", cSchCode);
            contentValues.put("SCR_CODE", cScrCode);
            contentValues.put("SEGMENT", cSegment);
            contentValues.put("BIRTH_DT", cBirthDate);
            contentValues.put("ENRL_DT", cEnrlDate);
            contentValues.put("INST_DT", cInstDate);
            contentValues.put("CUST_NAME", cCustName);
            contentValues.put("R_BLK_NO", cRBlockNo);
            contentValues.put("R_ADD1", cRAdd1);
            contentValues.put("R_ADD2", cRAdd2);
            contentValues.put("R_ADD3", cRAdd3);
            contentValues.put("R_CITY", cRCity);
            contentValues.put("R_PIN", cRPin);
            contentValues.put("R_PHONE", cRPhone);
            contentValues.put("M_PHONE", cMPhone);
            contentValues.put("O_NAME", cOName);
            contentValues.put("L_CLASS", cLclass);
            contentValues.put("NAT_BUS", cNatBus);
            contentValues.put("O_DEPT", cO_Dept);
            contentValues.put("O_DSGN", cO_Dsgn);
            contentValues.put("O_GROSS", cO_Gross);
            contentValues.put("O_NET", cO_Net);
            contentValues.put("O_ADD1", cO_Add1);
            contentValues.put("O_ADD2", cO_Add2);
            contentValues.put("O_ADD3", cO_Add3);
            contentValues.put("O_CITY", cO_City);
            contentValues.put("O_PIN", cO_Pin);
            contentValues.put("O_PHONE", cO_Phone);
            contentValues.put("OWN_HOUSE", cOwnHouse);
            contentValues.put("REFERENCE", cReference);
            contentValues.put("SPECIAL", cSpecial);
            contentValues.put("SALE_RATE", cSaleRate);
            contentValues.put("LOAN_AMT", cLoanAmount);
            contentValues.put("FC", cFc);
            contentValues.put("SALE_CR", cSaleCR);
            contentValues.put("PASS_CR", cPassCR);
            contentValues.put("ACCESS_CR", cAccessCR);
            contentValues.put("D_HPA_CR", cDHpaCR);
            contentValues.put("RTO_CR", cRtoCR);
            contentValues.put("MF_CR", cMfCR);
            contentValues.put("MISC_CR", cMiscCR);
            contentValues.put("SERV_CR", cServCR);
            contentValues.put("DOC_CR", cDocCR);
            contentValues.put("OBC_CR", cObcCR);
            contentValues.put("SITEV_CR", cSiteVCR);
            contentValues.put("SPDC_CR", cSpdcCR);
            contentValues.put("STAX_CR", cStaxCR);
            contentValues.put("INSU_CR", cInsuCR);
            contentValues.put("mont_int", cMontInt);
            contentValues.put("DPAY_AMT", cDpayAmt);
            contentValues.put("I_CQ", cI_CQ);
            contentValues.put("I_DT", cI_DT);
            contentValues.put("I_QAMOUNT", cI_QAmount);
            contentValues.put("I_CAMOUNT", cI_CAmount);
            contentValues.put("INST_NOS", cInstNos);
            contentValues.put("INST_AMT", cInstAmount);
            contentValues.put("EQU", cEqu);
            contentValues.put("INST_INT", cInstInt);
            contentValues.put("INST1_NOS", cInst1_Nos);
            contentValues.put("INST1_AMT", cInst1_Amt);
            contentValues.put("INST2_NOS", cInst2_Nos);
            contentValues.put("INST2_AMT", cInst2_Amt);
            contentValues.put("INST3_NOS", cInst3_Nos);
            contentValues.put("INST3_AMT", cInst3_Amt);
            contentValues.put("INST4_NOS", cInst4_Nos);
            contentValues.put("INST4_AMT", cInst4_Amt);
            contentValues.put("INST5_NOS", cInst5_Nos);
            contentValues.put("INST5_AMT", cInst5_Amt);
            contentValues.put("INST6_NOS", cInst6_Nos);
            contentValues.put("INST6_AMT", cInst6_Amt);
            contentValues.put("RFND_AMT", cRfndAmt);
            contentValues.put("ROI", cRoi);
            contentValues.put("SD_ADJ", cSdAdj);
            contentValues.put("SD_AMT", cSdAmt);
            contentValues.put("SD_INT_AMT", cSdIntAmt);
            contentValues.put("SD_RFND", cSdRfnd);
            contentValues.put("SUPL_CODE", cSuplCode);
            contentValues.put("SUPL_MORE", cSuplMore);
            contentValues.put("AMOUNT", cAmount);
            contentValues.put("ITEM_CODE", cItemCode);
            contentValues.put("CASH_CHQ", cCashChq);
            contentValues.put("NO_CHQS", cNoChqs);
            contentValues.put("EMI_AMT", cEmiAmt);
            contentValues.put("DOC_AMT", cDocAmt);
            contentValues.put("SERV_AMT", cServAmt);
            contentValues.put("SALE_AMT", cSaleAmt);
            contentValues.put("D_HPA_AMT", cDHpaAmt);
            contentValues.put("OTHER_AMT", cOtherAmt);
            contentValues.put("EMI", cEmi);
            contentValues.put("DEAL_COST", cDealCost);
            contentValues.put("HPA_AMT", cHpaAmt);
            contentValues.put("TENURE", cTenure);
            contentValues.put("CAN_FLAG", cCanFlag);
            contentValues.put("CHQS_RECD", cChqsRecvd);
            contentValues.put("MODE", cMode);
            contentValues.put("MARK_IRR", cMarkIrr);
            contentValues.put("RTO_ACC", cRtoAcc);
            contentValues.put("TDISCNT", cTDiscount);
            contentValues.put("DE_HOURS", cDeHours);
            contentValues.put("TODAY", cToday);
            contentValues.put("HOURS", cHours);
            contentValues.put("VERIFIEDBY", cVerifiedBy);
            contentValues.put("REMARK", cRemark);
            contentValues.put("SV_WHOM", cSvWhom);
            contentValues.put("DISBURSED", cDisbursed);
            contentValues.put("MGCH", cMgch);
            contentValues.put("OLD", cOld);
            contentValues.put("CLOSED", cClosed);
            contentValues.put("SH_NAME", cShName);
            contentValues.put("VEH_NO", cVehNo);
            contentValues.put("PAPER", cPaper);
            contentValues.put("C_CHQ_NO", cChqNo);
            contentValues.put("CBK_CODE", cBkCode);
            contentValues.put("C_BRANCH", cBranch);
            contentValues.put("PURPOSE", cPurpose);
            contentValues.put("CEN_RADD", cCenRAdd);
            contentValues.put("OCUST_CODE", cOCustCode);
            contentValues.put("DAC_CODE", cDacCode);
            contentValues.put("CUST_NAME1", custName1);
            contentValues.put("ITEM_NAME", itemName);
            contentValues.put("TRANCH_NO", tranchNo);
            contentValues.put("SRFND_AMT", srfndAmt);
            
            val = sqldb.update(DBHelper.CUSTMS, contentValues," CUST_CODE = '" + cCustCode + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }


    // Method to Fetch Data Based on Customer Code from CUSTOMER_MASTER

    public Cursor FetchDataFromCustomerMasterByCustomerCode(String custCode)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM CUSTMS WHERE CUST_CODE = '"+custCode+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data Based on Customer Code from GUARANTOR_MASTER

    public Cursor FetchDataFromGuarantorMasterByCustomerCode(String custCode)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM GRNTMS WHERE CUST_CODE = '"+custCode+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in GUARANTOR_MASTER

    public Cursor checkForDuplicateGuarantorMasterData(String custCode)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM GRNTMS WHERE CUST_CODE = '"+custCode+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long insertGuarantorMasterData(String gCustCode, String g1_Code, String g1_Bdt, String g1_Name, String g1_RAdd1, String g1_RAdd2, String g1_RAdd3, String g1_RLand, String g1_RCity, String g1_RPin, String g1_RPhone, String g1_O_Name, String g1_O_Dept, String g1_O_Dsgn, String g1_O_From, String g1_O_To, String g1_O_Sat, String g1_O_Gross, String g1_O_Net, String g1_O_Add1, String g1_O_Add2, String g1_O_Add3, String g1_O_Land, String g1_O_City, String g1_O_Pin, String g1_O_Phone, String g1_O_Ext, String g1_O_Fax, String g1_O_Page, String g1_O_Ref,
                                          String g2_Code, String g2_Bdt, String g2_Name, String g2_RAdd1, String g2_RAdd2, String g2_RAdd3, String g2_RLand, String g2_RCity, String g2_RPin, String g2_RPhone, String g2_O_Name, String g2_O_Dept, String g2_O_Dsgn, String g2_O_From, String g2_O_To, String g2_O_Sat, String g2_O_Gross, String g2_O_Net, String g2_O_Add1, String g2_O_Add2, String g2_O_Add3, String g2_O_Land, String g2_O_City, String g2_O_Pin, String g2_O_Phone, String g2_O_Ext, String g2_O_Fax, String g2_O_Page, String g2_O_Ref, String gShName)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("CUST_CODE", gCustCode);
            contentValues.put("G_CODE", g1_Code);
            contentValues.put("G1_BDT", g1_Bdt);
            contentValues.put("G_NAME", g1_Name);
            contentValues.put("G_R_ADD1", g1_RAdd1);
            contentValues.put("G_R_ADD2", g1_RAdd2);
            contentValues.put("G_R_ADD3", g1_RAdd3);
            contentValues.put("G_R_LAND", g1_RLand);
            contentValues.put("G_R_CITY", g1_RCity);
            contentValues.put("G_R_PIN", g1_RPin);
            contentValues.put("G_R_PHONE", g1_RPhone);
            contentValues.put("G_O_NAME", g1_O_Name);
            contentValues.put("G_O_DEPT", g1_O_Dept);
            contentValues.put("G_O_DSGN", g1_O_Dsgn);
            contentValues.put("G_O_FROM", g1_O_From);
            contentValues.put("G_O_TO", g1_O_To);
            contentValues.put("G_O_SAT", g1_O_Sat);
            contentValues.put("G_O_GROSS", g1_O_Gross);
            contentValues.put("G_O_NET", g1_O_Net);
            contentValues.put("G_O_ADD1", g1_O_Add1);
            contentValues.put("G_O_ADD2", g1_O_Add2);
            contentValues.put("G_O_ADD3", g1_O_Add3);
            contentValues.put("G_O_LAND", g1_O_Land);
            contentValues.put("G_O_CITY", g1_O_City);
            contentValues.put("G_O_PIN", g1_O_Pin);
            contentValues.put("G_O_PHONE", g1_O_Phone);
            contentValues.put("G_O_EXT", g1_O_Ext);
            contentValues.put("G_O_FAX", g1_O_Fax);
            contentValues.put("G_O_PAGE", g1_O_Page);
            contentValues.put("G_O_REF", g1_O_Ref);
            contentValues.put("G2_CODE", g2_Code);
            contentValues.put("G2_BDT", g2_Bdt);
            contentValues.put("G2_NAME", g2_Name);
            contentValues.put("G2_R_ADD1", g2_RAdd1);
            contentValues.put("G2_R_ADD2", g2_RAdd2);
            contentValues.put("G2_R_ADD3", g2_RAdd3);
            contentValues.put("G2_R_LAND", g2_RLand);
            contentValues.put("G2_R_CITY", g2_RCity);
            contentValues.put("G2_R_PIN", g2_RPin);
            contentValues.put("G2_R_PHONE", g2_RPhone);
            contentValues.put("G2_O_NAME", g2_O_Name);
            contentValues.put("G2_O_DSGN", g2_O_Dsgn);
            contentValues.put("G2_O_DEPT", g2_O_Dept);
            contentValues.put("G2_O_FROM", g2_O_From);
            contentValues.put("G2_O_TO", g2_O_To);
            contentValues.put("G2_O_SAT", g2_O_Sat);
            contentValues.put("G2_O_GROSS", g2_O_Gross);
            contentValues.put("G2_O_NET", g2_O_Net);
            contentValues.put("G2_O_ADD1", g2_O_Add1);
            contentValues.put("G2_O_ADD2", g2_O_Add2);
            contentValues.put("G2_O_ADD3", g2_O_Add3);
            contentValues.put("G2_O_LAND", g2_O_Land);
            contentValues.put("G2_O_CITY", g2_O_City);
            contentValues.put("G2_O_PIN", g2_O_Pin);
            contentValues.put("G2_O_PHONE", g2_O_Phone);
            contentValues.put("G2_O_EXT", g2_O_Ext);
            contentValues.put("G2_O_FAX", g2_O_Fax);
            contentValues.put("G2_O_PAGE", g2_O_Page);
            contentValues.put("G2_O_REF", g2_O_Ref);
            contentValues.put("SH_NAME", gShName);
            
            val = sqldb.insert(DBHelper.GRNTMS, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to Update GTOR_MASTER Data in SQLite Database

    public long updateGuarantorMasterData(String gCustCode, String g1_Code, String g1_Bdt, String g1_Name, String g1_RAdd1, String g1_RAdd2, String g1_RAdd3, String g1_RLand, String g1_RCity, String g1_RPin, String g1_RPhone, String g1_O_Name, String g1_O_Dept, String g1_O_Dsgn, String g1_O_From, String g1_O_To, String g1_O_Sat, String g1_O_Gross, String g1_O_Net, String g1_O_Add1, String g1_O_Add2, String g1_O_Add3, String g1_O_Land, String g1_O_City, String g1_O_Pin, String g1_O_Phone, String g1_O_Ext, String g1_O_Fax, String g1_O_Page, String g1_O_Ref,
                                          String g2_Code, String g2_Bdt, String g2_Name, String g2_RAdd1, String g2_RAdd2, String g2_RAdd3, String g2_RLand, String g2_RCity, String g2_RPin, String g2_RPhone, String g2_O_Name, String g2_O_Dept, String g2_O_Dsgn, String g2_O_From, String g2_O_To, String g2_O_Sat, String g2_O_Gross, String g2_O_Net, String g2_O_Add1, String g2_O_Add2, String g2_O_Add3, String g2_O_Land, String g2_O_City, String g2_O_Pin, String g2_O_Phone, String g2_O_Ext, String g2_O_Fax, String g2_O_Page, String g2_O_Ref, String gShName)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("G_CODE", g1_Code);
            contentValues.put("G1_BDT", g1_Bdt);
            contentValues.put("G_NAME", g1_Name);
            contentValues.put("G_R_ADD1", g1_RAdd1);
            contentValues.put("G_R_ADD2", g1_RAdd2);
            contentValues.put("G_R_ADD3", g1_RAdd3);
            contentValues.put("G_R_LAND", g1_RLand);
            contentValues.put("G_R_CITY", g1_RCity);
            contentValues.put("G_R_PIN", g1_RPin);
            contentValues.put("G_R_PHONE", g1_RPhone);
            contentValues.put("G_O_NAME", g1_O_Name);
            contentValues.put("G_O_DEPT", g1_O_Dept);
            contentValues.put("G_O_DSGN", g1_O_Dsgn);
            contentValues.put("G_O_FROM", g1_O_From);
            contentValues.put("G_O_TO", g1_O_To);
            contentValues.put("G_O_SAT", g1_O_Sat);
            contentValues.put("G_O_GROSS", g1_O_Gross);
            contentValues.put("G_O_NET", g1_O_Net);
            contentValues.put("G_O_ADD1", g1_O_Add1);
            contentValues.put("G_O_ADD2", g1_O_Add2);
            contentValues.put("G_O_ADD3", g1_O_Add3);
            contentValues.put("G_O_LAND", g1_O_Land);
            contentValues.put("G_O_CITY", g1_O_City);
            contentValues.put("G_O_PIN", g1_O_Pin);
            contentValues.put("G_O_PHONE", g1_O_Phone);
            contentValues.put("G_O_EXT", g1_O_Ext);
            contentValues.put("G_O_FAX", g1_O_Fax);
            contentValues.put("G_O_PAGE", g1_O_Page);
            contentValues.put("G_O_REF", g1_O_Ref);
            contentValues.put("G2_CODE", g2_Code);
            contentValues.put("G2_BDT", g2_Bdt);
            contentValues.put("G2_NAME", g2_Name);
            contentValues.put("G2_R_ADD1", g2_RAdd1);
            contentValues.put("G2_R_ADD2", g2_RAdd2);
            contentValues.put("G2_R_ADD3", g2_RAdd3);
            contentValues.put("G2_R_LAND", g2_RLand);
            contentValues.put("G2_R_CITY", g2_RCity);
            contentValues.put("G2_R_PIN", g2_RPin);
            contentValues.put("G2_R_PHONE", g2_RPhone);
            contentValues.put("G2_O_NAME", g2_O_Name);
            contentValues.put("G2_O_DSGN", g2_O_Dsgn);
            contentValues.put("G2_O_DEPT", g2_O_Dept);
            contentValues.put("G2_O_FROM", g2_O_From);
            contentValues.put("G2_O_TO", g2_O_To);
            contentValues.put("G2_O_SAT", g2_O_Sat);
            contentValues.put("G2_O_GROSS", g2_O_Gross);
            contentValues.put("G2_O_NET", g2_O_Net);
            contentValues.put("G2_O_ADD1", g2_O_Add1);
            contentValues.put("G2_O_ADD2", g2_O_Add2);
            contentValues.put("G2_O_ADD3", g2_O_Add3);
            contentValues.put("G2_O_LAND", g2_O_Land);
            contentValues.put("G2_O_CITY", g2_O_City);
            contentValues.put("G2_O_PIN", g2_O_Pin);
            contentValues.put("G2_O_PHONE", g2_O_Phone);
            contentValues.put("G2_O_EXT", g2_O_Ext);
            contentValues.put("G2_O_FAX", g2_O_Fax);
            contentValues.put("G2_O_PAGE", g2_O_Page);
            contentValues.put("G2_O_REF", g2_O_Ref);
            contentValues.put("SH_NAME", gShName);

            val = sqldb.update(DBHelper.GRNTMS, contentValues," CUST_CODE = '" + gCustCode + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in GUARANTOR_MASTER

    public Cursor checkForDuplicateFactSheetData(String custCode, String loanType)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_FACTSHEET WHERE CUST_CODE = '"+custCode+"' AND LOAN_TYPE = '"+loanType+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long insertFactSheetData(String actionId, String amtRecover, String amtPay, String appdAmt, String cCode, String name, String pDate, String estimateProperty, String fId, String firm, String fsId,
                                    String hProp, String broInc, String dauInc, String fathInc, String mothInc, String selfInc, String sisInc, String sonInc, String spouseInc, String totalInc, String ipAdd,
                                    String ledBal, String legalProcess, String legalType, String loanType, String mobile, String natBus, String buildingname, String oLanMark, String oArea, String oCity,
                                    String oName, String oPhoneNo, String oPinCode, String oProperty, String occId, String odInstAmt, String odInstno, String oldfid, String pdcStatus, String product,
                                    String propertycase, String society, String landmark, String area, String blockno, String city, String phoneno, String pincode, String taluka, String readytopay,
                                    String remark, String remarkcase, String remarkcode, String segment, String serTypeId, String sysDate, String recoverPeriod, String totalFamily, String uName)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("F_ID", fId);
            contentValues.put("CUST_CODE", cCode);
            contentValues.put("LOAN_TYPE", loanType);
            contentValues.put("CUST_NAME", name);
            contentValues.put("LEDGER_BAL", ledBal);
            contentValues.put("ODUE_INSTNO", odInstno);
            contentValues.put("ODUE_INSTAMT", odInstAmt);
            contentValues.put("APRROD_AMT", appdAmt);
            contentValues.put("SEGMENT", segment);
            contentValues.put("PRODUCT", product);
            contentValues.put("R_BLOCKNO", blockno);
            contentValues.put("R_ADD1", society);
            contentValues.put("R_ADD2", landmark);
            contentValues.put("R_ADD3", area);
            contentValues.put("R_TALUKA", taluka);
            contentValues.put("R_CITY", city);
            contentValues.put("R_PIN", pincode);
            contentValues.put("R_PHONE", phoneno);
            contentValues.put("MOBILE", mobile);
            contentValues.put("O_NAME", oName);
            contentValues.put("O_ADD1", buildingname);
            contentValues.put("O_ADD2", oLanMark);
            contentValues.put("O_ADD3", oArea);
            contentValues.put("O_CITY", oCity);
            contentValues.put("O_PIN", oPinCode);
            contentValues.put("O_PHONE", oPhoneNo);
            contentValues.put("PDC_STATUS", pdcStatus);
            contentValues.put("LEGAL_PROCESS", legalProcess);
            contentValues.put("LEGAL_TYPE", legalType);
            contentValues.put("PROPRIETORY_ID", propertycase);
            contentValues.put("FS_ID", fsId);
            contentValues.put("OCC_ID", occId);
            contentValues.put("NATBUSI_ID", natBus);
            contentValues.put("SERTYPE_ID", serTypeId);
            contentValues.put("FIRMNAME", firm);
            contentValues.put("INC_SELF", selfInc);
            contentValues.put("INC_FATHER", fathInc);
            contentValues.put("INC_MOTHER", mothInc);
            contentValues.put("INC_SPOUSE", spouseInc);
            contentValues.put("INC_BROTHER", broInc);
            contentValues.put("INC_SISTER", sisInc);
            contentValues.put("INC_SON", sonInc);
            contentValues.put("INC_DAUGHTER", dauInc);
            contentValues.put("INC_TOTAL", totalInc);
            contentValues.put("TOTAL_MEMBER", totalFamily);
            contentValues.put("EST_PROPERTY", estimateProperty);
            contentValues.put("H_PRO_ID", hProp);
            contentValues.put("O_PRO_ID", oProperty);
            contentValues.put("AMTRECOVER_MAS", amtRecover);
            contentValues.put("READYTOPAY", readytopay);
            contentValues.put("TIMERECOVER", recoverPeriod);
            contentValues.put("AMTTOPAY", amtPay);
            contentValues.put("DATEOFPTP", pDate);
            contentValues.put("REMARKCODE_ID", remarkcode);
            contentValues.put("REMARKCASE_ID", remarkcase);
            contentValues.put("ACTION_ID", actionId);
            contentValues.put("USERNAME", uName);
            contentValues.put("IPADDRESS", ipAdd);
            contentValues.put("SYSDATE", sysDate);
            contentValues.put("REMARK", remark);
            contentValues.put("OLDFIDNO", oldfid);

            val = sqldb.insert(DBHelper.F_FACTSHEET, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to update GTOR_MASTER Data in SQLite Database

    public long updateFactSheetData(String actionId, String amtRecover, String amtPay, String appdAmt, String cCode, String name, String pDate, String estimateProperty, String fId, String firm, String fsId,
                                    String hProp, String broInc, String dauInc, String fathInc, String mothInc, String selfInc, String sisInc, String sonInc, String spouseInc, String totalInc, String ipAdd,
                                    String ledBal, String legalProcess, String legalType, String loanType, String mobile, String natBus, String buildingname, String oLanMark, String oArea, String oCity,
                                    String oName, String oPhoneNo, String oPinCode, String oProperty, String occId, String odInstAmt, String odInstno, String oldfid, String pdcStatus, String product,
                                    String propertycase, String society, String landmark, String area, String blockno, String city, String phoneno, String pincode, String taluka, String readytopay, String remark, String remarkcase, String remarkcode, String segment, String serTypeId, String sysDate, String recoverPeriod, String totalFamily, String uName)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("F_ID", fId);
            contentValues.put("CUST_CODE", cCode);
            contentValues.put("LOAN_TYPE", loanType);
            contentValues.put("CUST_NAME", name);
            contentValues.put("LEDGER_BAL", ledBal);
            contentValues.put("ODUE_INSTNO", odInstno);
            contentValues.put("ODUE_INSTAMT", odInstAmt);
            contentValues.put("APRROD_AMT", appdAmt);
            contentValues.put("SEGMENT", segment);
            contentValues.put("PRODUCT", product);
            contentValues.put("R_BLOCKNO", blockno);
            contentValues.put("R_ADD1", society);
            contentValues.put("R_ADD2", landmark);
            contentValues.put("R_ADD3", area);
            contentValues.put("R_TALUKA", taluka);
            contentValues.put("R_CITY", city);
            contentValues.put("R_PIN", pincode);
            contentValues.put("R_PHONE", phoneno);
            contentValues.put("MOBILE", mobile);
            contentValues.put("O_NAME", oName);
            contentValues.put("O_ADD1", buildingname);
            contentValues.put("O_ADD2", oLanMark);
            contentValues.put("O_ADD3", oArea);
            contentValues.put("O_CITY", oCity);
            contentValues.put("O_PIN", oPinCode);
            contentValues.put("O_PHONE", oPhoneNo);
            contentValues.put("PDC_STATUS", pdcStatus);
            contentValues.put("LEGAL_PROCESS", legalProcess);
            contentValues.put("LEGAL_TYPE", legalType);
            contentValues.put("PROPRIETORY_ID", propertycase);
            contentValues.put("FS_ID", fsId);
            contentValues.put("OCC_ID", occId);
            contentValues.put("NATBUSI_ID", natBus);
            contentValues.put("SERTYPE_ID", serTypeId);
            contentValues.put("FIRMNAME", firm);
            contentValues.put("INC_SELF", selfInc);
            contentValues.put("INC_FATHER", fathInc);
            contentValues.put("INC_MOTHER", mothInc);
            contentValues.put("INC_SPOUSE", spouseInc);
            contentValues.put("INC_BROTHER", broInc);
            contentValues.put("INC_SISTER", sisInc);
            contentValues.put("INC_SON", sonInc);
            contentValues.put("INC_DAUGHTER", dauInc);
            contentValues.put("INC_TOTAL", totalInc);
            contentValues.put("TOTAL_MEMBER", totalFamily);
            contentValues.put("EST_PROPERTY", estimateProperty);
            contentValues.put("H_PRO_ID", hProp);
            contentValues.put("O_PRO_ID", oProperty);
            contentValues.put("AMTRECOVER_MAS", amtRecover);
            contentValues.put("READYTOPAY", readytopay);
            contentValues.put("TIMERECOVER", recoverPeriod);
            contentValues.put("AMTTOPAY", amtPay);
            contentValues.put("DATEOFPTP", pDate);
            contentValues.put("REMARKCODE_ID", remarkcode);
            contentValues.put("REMARKCASE_ID", remarkcase);
            contentValues.put("ACTION_ID", actionId);
            contentValues.put("USERNAME", uName);
            contentValues.put("IPADDRESS", ipAdd);
            contentValues.put("SYSDATE", sysDate);
            contentValues.put("REMARK", remark);
            contentValues.put("OLDFIDNO", oldfid);

            val = sqldb.update(DBHelper.F_FACTSHEET, contentValues," CUST_CODE = '" + cCode + "' AND LOAN_TYPE = '"+loanType+"' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor checkForDuplicateFactSheetOccupation(String occName)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_OCCUPATION WHERE OCC_NAME = '"+occName+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long insertFactSheetOccupation(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("OCC_ID", id);
            contentValues.put("OCC_NAME", name);
            contentValues.put("EXIST", exist);

            val = sqldb.insert(DBHelper.F_OCCUPATION, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long updateFactSheetOccupation(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("OCC_ID", id);
            contentValues.put("OCC_NAME", name);
            contentValues.put("EXIST", exist);
            
            val = sqldb.update(DBHelper.F_OCCUPATION, contentValues," OCC_NAME = '" + name + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor checkForDuplicateFactSheetAction(String actionName)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_ACTION WHERE ACTION_NAME = '"+actionName+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long insertFactSheetAction(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("ACTION_ID", id);
            contentValues.put("ACTION_NAME", name);
            contentValues.put("EXIST", exist);

            val = sqldb.insert(DBHelper.F_ACTION, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long updateFactSheetAction(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("ACTION_ID", id);
            contentValues.put("ACTION_NAME", name);
            contentValues.put("EXIST", exist);
            
            val = sqldb.update(DBHelper.F_ACTION, contentValues," ACTION_NAME = '" + name + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor checkForDuplicateFSPresence(String name)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_FACTSHEETPRESENCE WHERE FS_NAME = '"+name+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long insertFactSheetPresence(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("FS_ID", id);
            contentValues.put("FS_NAME", name);
            contentValues.put("EXIST", exist);

            val = sqldb.insert(DBHelper.F_FACTSHEET_PRESENCE, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long updateFactSheetPresence(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("FS_ID", id);
            contentValues.put("FS_NAME", name);
            contentValues.put("EXIST", exist);
            
            val = sqldb.update(DBHelper.F_FACTSHEET_PRESENCE, contentValues," FS_NAME = '" + name + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor checkForDuplicateProperty(String name)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_PROPERTY WHERE PRO_NAME = '"+name+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long insertFactSheetProperty(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("PRO_ID", id);
            contentValues.put("PRO_NAME", name);
            contentValues.put("EXIST", exist);

            val = sqldb.insert(DBHelper.F_PROPERTY, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long updateFactSheetProperty(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("PRO_ID", id);
            contentValues.put("PRO_NAME", name);
            contentValues.put("EXIST", exist);
            
            val = sqldb.update(DBHelper.F_PROPERTY, contentValues," PRO_NAME = '" + name + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor checkForDuplicateServiceData(String name)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_SERVICETYPE WHERE SERTYPE_NAME = '"+name+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long insertFactSheetServiceType(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("SERTYPE_ID", id);
            contentValues.put("SERTYPE_NAME", name);
            contentValues.put("EXIST", exist);

            val = sqldb.insert(DBHelper.F_SERVICETYPE, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long updateFactSheetServiceType(String id, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("SERTYPE_ID", id);
            contentValues.put("SERTYPE_NAME", name);
            contentValues.put("EXIST", exist);
            
            val = sqldb.update(DBHelper.F_SERVICETYPE, contentValues," SERTYPE_NAME = '" + name + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor checkForDuplicateRemarkCase(String rpay, String name)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_REMARKCASE WHERE REMARKCASE = '"+name+"' AND READYTOPAY = '"+rpay+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long insertFactSheetRemarkCase(String id, String rPay, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("REMARKCASE_ID", id);
            contentValues.put("REMARKCASE", name);
            contentValues.put("READYTOPAY", rPay);
            contentValues.put("EXIST", exist);

            val = sqldb.insert(DBHelper.F_REMARKCASE, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long updateFactSheetRemarkCase(String id, String rPay, String name, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("REMARKCASE_ID", id);
            contentValues.put("REMARKCASE", name);
            contentValues.put("READYTOPAY", rPay);
            contentValues.put("EXIST", exist);

            val = sqldb.update(DBHelper.F_REMARKCASE, contentValues," REMARKCASE = '" + name + "' AND READYTOPAY = '"+rPay+"'  ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor checkForDuplicateTaluka(String distCode, String taluName)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM TALUKA WHERE DIST_CODE = '"+distCode+"' AND TALU_NAME = '"+taluName+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long insertTalukaData(String taluCode, String distCode, String taluName, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("TALU_CODE", taluCode);
            contentValues.put("TALU_NAME", taluName);
            contentValues.put("DIST_CODE", distCode);
            contentValues.put("EXIST", exist);

            val = sqldb.insert(DBHelper.TALUKA, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to insert GTOR_MASTER Data in SQLite Database

    public long updateTalukaData(String taluCode, String distCode, String taluName, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("TALU_CODE", taluCode);
            contentValues.put("TALU_NAME", taluName);
            contentValues.put("DIST_CODE", distCode);
            contentValues.put("EXIST", exist);

            val = sqldb.update(DBHelper.TALUKA, contentValues," TALU_CODE = '" + taluCode + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor getFactSheetDatabyCustCode(String code)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_FACTSHEET WHERE CUST_CODE = '"+code+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor GetCustomerDataByCode(String code)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM CUSTMS WHERE CUST_CODE = '"+code+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor GetGuarantorMasterDataByCode(String code)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM GRNTMS WHERE CUST_CODE = '"+code+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor GetOccupationDataForFactSheetOffline(String isExist)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_OCCUPATION WHERE EXIST = '"+isExist+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor GetFeedBackDataForFactSheetOffline(String isExist)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM FEEDBACK WHERE EXIST = '"+isExist+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor GetActionDataForFactSheetOffline(String isExist)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_ACTION WHERE EXIST = '"+isExist+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor GetFsPresenceDataForFactSheetOffline(String isExist)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_FACTSHEETPRESENCE WHERE EXIST = '"+isExist+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor GetPropertyDataForFactSheetOffline(String isExist)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_PROPERTY WHERE EXIST = '"+isExist+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor GetServiceTypeDataForFactSheetOffline(String isExist)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_SERVICETYPE WHERE EXIST = '"+isExist+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor GetRemarkCaseDataForFactSheetOffline(String isExist)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_REMARKCASE WHERE EXIST = '"+isExist+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in TALUKA

    public Cursor GetTalukaDataForFactSheetOffline(String isExist)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM TALUKA WHERE EXIST = '"+isExist+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in F_FACTSHEET_OCCUPATION

    public Cursor GetAllFactSheetData(String code, String name, String type)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_FACTSHEET WHERE CUST_CODE = '"+code+"' AND LOAN_TYPE = '"+type+"' AND CUST_NAME = '"+name+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in PLAN_VISIT Table

    public Cursor checkForPlanVisitData(String planId)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PLAN_VISIT WHERE ID = '"+planId+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to update PLAN_VISIT Data in SQLite Database

    public long updatePlanVisitDataAfterFactSheet(String planId, String bid, String ctype, String cid, String collection, String date, String isPlanned, String isactive, String isdelete, String issync, String notes, String ptpDate, String sid, String status, String trgId, String vType)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("TARGET_MASTER_ID", trgId);
            contentValues.put("VISIT_TYPE_PLANNED", vType);
            contentValues.put("COLLECTION_PLANNED", collection);
            contentValues.put("CASE_TYPE", ctype);
            contentValues.put("PTP_DATE", ptpDate);
            contentValues.put("IS_ACTIVE_VISIT", 0);
            contentValues.put("IS_PLANNED_VISIT", isPlanned);
            contentValues.put("BID", bid);
            contentValues.put("CID", cid);
            contentValues.put("ISACTIVE", isactive);
            contentValues.put("ISDELETE", isdelete);
            contentValues.put("ENTRYDATE", date);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", issync);
            contentValues.put("SID", sid);
            contentValues.put("NOTES", notes);

            val = sqldb.update(DBHelper.PLAN_VISIT, contentValues," ID = '" + planId + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to Fetch Data from VISIT_COLLECTION

    public Cursor getAllFactSheetData()
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM F_FACTSHEET ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Delete Data from F_FACTSHEET

    public void deleteFactSheetData()
    {
        try
        {
            openToWrite();
            sqldb.delete(DBHelper.F_FACTSHEET, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Delete Data from CUST_MS

    public void deleteCustomerMasterData()
    {
        try
        {
            openToWrite();
            sqldb.delete(DBHelper.CUSTMS, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // Delete Data from GRNT_MS

    public void deleteGrntMasterData()
    {
        try
        {
            openToWrite();
            sqldb.delete(DBHelper.GRNTMS, null, null);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    // Method to Fetch Data from VISIT_COLLECTION

    public Cursor GetPlannedVisitCnt(String uId, String isPlanned, String isDelete)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PLAN_VISIT WHERE USER_ID = '"+uId+"' AND IS_PLANNED_VISIT = '"+isPlanned+"' AND ISDELETE = '"+isDelete+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data from VISIT_COLLECTION

    public Cursor GetPlannedCompletedVisitCnt(String uId, String isActiveVisit, String isPlanned, String isDelete)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PLAN_VISIT WHERE USER_ID = '"+uId+"' AND IS_ACTIVE_VISIT = '"+isActiveVisit+"' AND IS_PLANNED_VISIT = '"+isPlanned+"' AND ISDELETE = '"+isDelete+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }

    // Method to Fetch Data from VISIT_COLLECTION

    public Cursor GetUnPlannedCompletedVisit(String uId, String isPlanned, String isDelete)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PLAN_VISIT WHERE USER_ID = '"+uId+"' AND IS_PLANNED_VISIT = '"+isPlanned+"' AND ISDELETE = '"+isDelete+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data from VISIT_COLLECTION

    public Cursor GetPlannedPendingVisit(String uId, String isActiveVisit, String isPlanned, String isDelete)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PLAN_VISIT WHERE USER_ID = '"+uId+"' AND IS_ACTIVE_VISIT = '"+isActiveVisit+"' AND IS_PLANNED_VISIT = '"+isPlanned+"' AND ISDELETE = '"+isDelete+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data from SETTING_MASTER

    public Cursor getSettingMasterData()
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM SETTING_MASTER WHERE ISDELETE IN(0) ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data from PRINTER_SETTING

    public Cursor getPrinterSettingData()
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PRINTER_SETTING WHERE ISDELETE IN(0) ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data from TIME_MASTER

    public Cursor getTimeMasterData()
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM TIME_MASTER ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data Form BANK_MASTER_RMS

    public Cursor fetchBankMasterRmsData()
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM BANK_MASTER_RMS ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Fetch Data Form BANK_MASTER_MFSL

    public Cursor fetchBankMasterMFSLData()
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM BANK_MASTER_MFSL ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to check for duplicate entries in TARGET_MASTER

    public Cursor checkForBankMasterRMSData(String bankId)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM BANK_MASTER_RMS WHERE BANK_ID = '"+bankId+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Insert BANK_MASTER Data in SQLite Database

    public long insertBankMasterRMSData(String bankId, String bankCode, String bankName, String compCode, String crdtCode, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("BANK_ID", bankId);
            contentValues.put("COMP_CODE", compCode);
            contentValues.put("BANK_CODE", bankCode);
            contentValues.put("BANK_NAME", bankName);
            contentValues.put("CRDT_CODE", crdtCode);
            contentValues.put("EXIST", exist);

            val = sqldb.insert(DBHelper.BANK_MASTER_RMS, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to Update BANK_MASTER Data in SQLite Database

    public long updateBankMasterRMSData(String bankId, String bankCode, String bankName, String compCode, String crdtCode, String exist)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("COMP_CODE", compCode);
            contentValues.put("BANK_CODE", bankCode);
            contentValues.put("BANK_NAME", bankName);
            contentValues.put("CRDT_CODE", crdtCode);
            contentValues.put("EXIST", exist);

            val = sqldb.update(DBHelper.BANK_MASTER_RMS, contentValues," BANK_ID = '" + bankId + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in TARGET_MASTER

    public Cursor checkForBankMasterMFSLData(String bankName, String bankCode)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM BANK_MASTER_MFSL WHERE BANK_NAME = '"+bankName+"' AND BANK_CODE = '"+bankCode+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Insert BANK_MASTER Data of MFSL in SQLite Database

    public long insertBankMasterMFSLData(String compCode, String bankCode, String bankName, String newCode, String clearingCd, String close, String closeDt)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("COMP_CODE", compCode);
            contentValues.put("BANK_CODE", bankCode);
            contentValues.put("BANK_NAME", bankName);
            contentValues.put("NEW_CODE", newCode);
            contentValues.put("CLEARINGCD", clearingCd);
            contentValues.put("CLOSE", close);
            contentValues.put("CLOSE_DT", closeDt);

            val = sqldb.insert(DBHelper.BANK_MASTER_MFSL, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to Update BANK_MASTER Data of MFSL in SQLite Database

    public long updateBankMasterMFSLData(String compCode, String bankCode, String bankName, String newCode, String clearingCd, String close, String closeDt)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("COMP_CODE", compCode);
            contentValues.put("BANK_CODE", bankCode);
            contentValues.put("BANK_NAME", bankName);
            contentValues.put("NEW_CODE", newCode);
            contentValues.put("CLEARINGCD", clearingCd);
            contentValues.put("CLOSE", close);
            contentValues.put("CLOSE_DT", closeDt);

            val = sqldb.update(DBHelper.BANK_MASTER_MFSL, contentValues," BANK_NAME = '"+bankName+"' AND BANK_CODE = '"+bankCode+"' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in SETTING_MASTER

    public Cursor checkForSettingMasterData(String id)
    {
       openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM SETTING_MASTER WHERE ID = '"+id+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Insert BANK_MASTER Data in SQLite Database

    public long insertSettingMasterData(String id, String dayBeginTime, String dayEndTime, String minPlanVisitPerDay, String maxPlanVisitPerDay, String maxPtpAllowedPerMonth, String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("ID", id);
            contentValues.put("DAY_BEGIN_TIME", dayBeginTime);
            contentValues.put("DAY_END_TIME", dayEndTime);
            contentValues.put("MIN_PLAN_VISIT_PER_DAY", minPlanVisitPerDay);
            contentValues.put("MAX_PLAN_VISIT_PER_DAY", maxPlanVisitPerDay);
            contentValues.put("MAX_PTP_ALLOWED_PER_MONTH", maxPtpAllowedPerMonth);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);

            val = sqldb.insert(DBHelper.SETTING_MASTER, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to Update BANK_MASTER Data in SQLite Database

    public long updateSettingMasterData(String id, String dayBeginTime, String dayEndTime, String minPlanVisitPerDay, String maxPlanVisitPerDay, String maxPtpAllowedPerMonth, String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("DAY_BEGIN_TIME", dayBeginTime);
            contentValues.put("DAY_END_TIME", dayEndTime);
            contentValues.put("MIN_PLAN_VISIT_PER_DAY", minPlanVisitPerDay);
            contentValues.put("MAX_PLAN_VISIT_PER_DAY", maxPlanVisitPerDay);
            contentValues.put("MAX_PTP_ALLOWED_PER_MONTH", maxPtpAllowedPerMonth);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);

            val = sqldb.update(DBHelper.SETTING_MASTER, contentValues," ID = '" + id + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to check for duplicate entries in PRINTER_SETTING

    public Cursor checkForPrinterSettingData(String id)
    {
        openToRead();
        Cursor c = null;
        String q1 = " SELECT * FROM PRINTER_SETTING WHERE ID = '"+id+"' ";

        try
        {
            c = sqldb.rawQuery(q1, null);
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to Insert PRINTER_SETTING Data in SQLite Database

    public long insertPrinterSettingData(String id, String cashReceiptAllow, String chequeReceiptAllow, String ddReceiptAllow, String neftReceiptAllow, String rtgsReceiptAllow, String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("ID", id);
            contentValues.put("CASH_RECEIPT_ALLOWED", cashReceiptAllow);
            contentValues.put("CHEQUE_RECEIPT_ALLOWED", chequeReceiptAllow);
            contentValues.put("DD_RECEIPT_ALLOWED", ddReceiptAllow);
            contentValues.put("NEFT_RECEIPT_ALLOWED", neftReceiptAllow);
            contentValues.put("RTGS_RECEIPT_ALLOWED", rtgsReceiptAllow);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);

            val = sqldb.insert(DBHelper.PRINTER_SETTING, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to Update PRINTER_SETTING Data in SQLite Database

    public long updatePrinterSettingData(String id, String cashReceiptAllow, String chequeReceiptAllow, String ddReceiptAllow, String neftReceiptAllow, String rtgsReceiptAllow, String bId, String cId, String isActive, String isDelete, String entryDate, String status, String isSync, String sId, String notes)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("ID", id);
            contentValues.put("CASH_RECEIPT_ALLOWED", cashReceiptAllow);
            contentValues.put("CHEQUE_RECEIPT_ALLOWED", chequeReceiptAllow);
            contentValues.put("DD_RECEIPT_ALLOWED", ddReceiptAllow);
            contentValues.put("NEFT_RECEIPT_ALLOWED", neftReceiptAllow);
            contentValues.put("RTGS_RECEIPT_ALLOWED", rtgsReceiptAllow);
            contentValues.put("BID", bId);
            contentValues.put("CID", cId);
            contentValues.put("ISACTIVE", isActive);
            contentValues.put("ISDELETE", isDelete);
            contentValues.put("ENTRYDATE", entryDate);
            contentValues.put("STATUS", status);
            contentValues.put("ISSYNC", isSync);
            contentValues.put("SID", sId);
            contentValues.put("NOTES", notes);

            val = sqldb.update(DBHelper.PRINTER_SETTING, contentValues," ID = '" + id + "' ", null);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return val;
    }


    // Method to Get the Collection Amount for User based on USER_ID and DATE

    public Cursor GetCollectionLimitAmount(String userId, String fromDate, String toDate)
    {
        openToRead();
        Cursor c = null;
        String q2 = " SELECT SUM(VISIT_COLLECTION.AMOUNT) AS COLLECTION_LIMIT FROM VISIT_COLLECTION " +
                " LEFT JOIN PLAN_VISIT ON VISIT_COLLECTION.PLAN_VISIT_ID = PLAN_VISIT.ID" +
                " LEFT JOIN USER_MAS ON USER_MAS.ID = PLAN_VISIT.USER_ID" +
                " WHERE USER_MAS.ID = '"+ userId +"' " +
                " AND VISIT_COLLECTION.VISIT_TYPE_ACTUAL = '1' " +
                " AND VISIT_COLLECTION.ISDELETE = '0' " +
                " AND VISIT_COLLECTION.ENTRYDATE >= '"+ fromDate +"' " +
                " AND VISIT_COLLECTION.ENTRYDATE <= '"+ toDate +"' ";

        try
        {
            c = sqldb.rawQuery(q2, null);
            int cnt = c.getCount();
            return c;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }


    // Method to insert TIME_MASTER Data in SQLite Database

    public long SaveTimeMasterData(String dayBeginTime, String dayEndTime)
    {
        openToWrite();
        long val = 0;

        try
        {
            ContentValues contentValues = new ContentValues();

            contentValues.put("DAY_BEGIN_TIME", dayBeginTime);
            contentValues.put("DAY_END_TIME", dayEndTime);

            val = sqldb.insert(DBHelper.TIME_MASTER, null, contentValues);
            return val;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return val;
    }
}

*/
