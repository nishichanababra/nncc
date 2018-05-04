/*
package app.citta.utilities.GeneralizedModules.Firebase.Reciever;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import app.mas.rms.App.CommonFunctions;
import app.mas.rms.App.Config;
import app.mas.rms.App.DialogConfig;
import app.mas.rms.App.checkNetwork;
import app.mas.rms.DBFile.DBAdapter;
import app.mas.rms.DataLoader.CustomExpandableListAdapter;
import app.mas.rms.DataLoader.ExpandableDataListLoader;
import app.mas.rms.R;
import app.mas.rms.ServiceClass.UpdateDataService;

public class FieldVisitCustomerDetailsPlanned extends AppCompatActivity implements View.OnClickListener
{
    Button btnAction;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    LinkedHashMap<String, List<String>> expandableListDetail;
    TextView txtcustomerName,txtcustomerCode;
    DBAdapter dbAdapter;

    // Mode of Action for Field Visit like COLLECTION, PTP, FILE, etc.
    private String[] actions = {Config.COLLECTION, Config.PTP, Config.SEIZE, Config.FILE, Config.REMARK};

    private Calendar cal, start;
    private int startYear, startMonth, startDay, endYear, endMonth, endDay, resYear, resMonth;

    String MAX_PTP_ALLOWED;


    */
/********** Variables Used for holding Expandable List Values ***********//*


    String customerCode, custName, custDateofBirth, custRblockNo, custAdd1, custAdd2, custAdd3, custCity, custPin, custRPhone, custMPhone;

    String officeName, officeAdd1, officeAdd2, officeAdd3, officeCity, officePin, officeRPhone;

    String g1Name, g1BlockNo, g1Add1, g1Add2,g1Add3, g1City, g1Pin, g1RPhone;

    String g2Name, g2BlockNo, g2Add1, g2Add2,g2Add3, g2City, g2Pin, g2RPhone;

    String loanAmount, loanDate, installmentDate, lastInstallmentDate, inst1Amount, noOfInstallment, segment, itemName, vehicleNo, engineNo, chasisNo, dealerName, marketingExecutive;

    String returnCheque, odueInstallmentNo, dueInstallmentAmt, approadAmount, capOs, charges, customerType, caseRemarks, remarkOfReturnCaseForFastCollection;

    String chqDtM1, chqDtM2, chqDtM3, chqNoM1, chqNoM2, chqNoM3, chqRtnRsnM1, chqRtnRsnM2, chqRtnRsnM3;

    String legalCaseType, stageCriminal, caseFileDateCriminal, claimAmountCriminal, nextDateCriminal, stageCivil, caseFileDateCivil, claimAmountCivil, nextDateCivil, stageArbitration, caseFileDateArbitration, arbitrationClaimAmount;

    */
/****************//*


    String userId, ImeiNo, isOnlineUser, execCode, trgMastId, planVisitId, feedBackResponse, startDate, endDate, ptpCountCustomer;

    String visitTypeActual, entryDate, todayDate;

    String feedBackId, feedBackName, feedBackExist, visitCollectionId;

    int visitCount, feedBackCnt, userAccess, custPTPCount = 0, maxPTP = 0;

    double fileLoanAmt = 0.0, fileEmi = 0.0, fileRoi = 0.0, fileTenor = 0.0;

    DateFormat originalFormat = new SimpleDateFormat(Config.DD_MM_YYYY, Locale.ENGLISH);
    DateFormat targetFormat = new SimpleDateFormat(Config.SOAP_DATE_FORMAT);
    DateFormat trgForm = new SimpleDateFormat(Config.YYYY_MM_DD);

    Date cDate, datePTPMode;

    */
/************ VARIABLES USED FOR COLLECTION_LIMIT APPROVAL DIALOG *************//*


    public static Dialog customAlertDialog = null, dialogApproval = null;

    EditText txtOtherReason;
    Button btnTakeApprove, btnSendApprove;
    TextView tv_alert_msg, tv_main_title;

    String approvalFor = "", approvalDate = "", approvalReason = "", approvalDescription = "", isApprove = "0", approvalComment = "", extra1 = "", extra2 = "", approvalStatus = "0", approvalEntryDate = "";

    */
/*************************//*


    public static Dialog dialogPtpMode = null, dialogFileMode, dialogSeizeMode, dialogRemarkMode;

    // Variables Used for REMARK Dialog

    Spinner spRemark;
    Button btnRemarkSubmit;

    // Variables Used for PTP Dialog (CASH MODE)

    RadioGroup rbGrpPtpMode;
    RadioButton rbPtpMode, rbPtpCash, rbPtpFile, rbPtpSeize;
    RelativeLayout rvPtpCashDatePicker, rvPtpCashTimePicker;
    EditText txtCashPtpDate, txtCashPtpTime, txtCashPtpAmount, txtCustomerMobile;
    Button btnPtpSubmit, btnFileSubmit;
    LinearLayout lvCashDate, lvCashTime, lvCashAmount;

    // Variables Used for PTP Dialog (FILE MODE)

    RelativeLayout rvPtpFileDatePicker;
    EditText txtFilePtpDate, txtFileApprovalAmount, txtFileDpAmount, txtFileLoanAmount, txtFileNoOfInstallment, txtFileInstallmentAmount;
    RadioGroup rbGrpLoanCalc;
    RadioButton rbLoanCalcType;
    RadioButton rbLoanAmt, rbEmi, rbRoi, rbTenure;
    EditText txtLoanAmount, txtEmi, txtRoi, txtTenure;
    Button btnPTPLoanCalculate, btnFileLoanCalculate;
    LinearLayout lvFileDate, lvFileApproveAmt, lvFileDpAmt, lvFileLoanAmt, lvFileInstallmentNo, lvFileInstallmentAmt, lvLoanCalcTitle, lvLoanCalcSelection, lvLoanAmount, lvLoanAmt, lvLoanEmi, lvLoanRoi, lvLoanTenure, lvLoanCalculate;


    // Variables Used for PTP Dialog (SEIZE MODE)

    RelativeLayout rvPtpSeizeDatePicker;
    EditText txtSeizePtpDate, txtVehicleName, txtVehicleNo, txtEngineNo, txtChasisNo, txtPoliceStationName, txtPolicStationCenter, txtSeizeAgencyName, txtPoliceStationFAX, txtPoliceStationEmail;
    LinearLayout lvSeizeDate, lvVehicleName, lvVehicleNo, lvChasisNo, lvEngineNo, lvPsName, lvPsCenter, lvAgencyName, lvPsFaxNo, lvPsEmail;


    // Variables Used for FILE Dialog (LOANEE)

    RadioGroup rbGrpLoanee, rbGrpLoaneePhoto, rbGrpLoaneeResdProof, rbGrpLoaneeIDProof, rbGrpLoaneeSecurityCheque;
    EditText txtLoaneeResdProofOthers, txtLoaneeIDProofOthers;
    RadioButton rbLoanee, rbLoaneePhoto, rbLoaneeResdProof, rbLoaneeIDProof, rbLoaneeSecurityCheque;
    RadioButton rbLoaneeYes, rbLoaneeNo, rbLoaneePhotoYes, rbLoaneePhotoNo, rbLoaneeResdProofYes, rbLoaneeResdProofNo, rbLoaneeIdProofYes, rbLoaneeIdProofNo, rbLoaneeSecurityChequeYes, rbLoaneeSecurityChequeNo;
    CheckBox chLoaneeResdLightBill, chLoaneeResdTaxBill, chLoaneeResdAadharCard, chLoaneeResdOther;
    CheckBox chLoaneeIDPanCard, chLoaneeIDElectionCard, chLoaneeIDAadharCard, chLoaneeIDDrivingLicence, chLoaneeIDOther;
    LinearLayout lvLoaneePhoto, lvLoaneeResdProof, lvLoaneeResdProofDetails, lvLoaneeIDProof, lvLoaneeIDProofDetails, lvLoaneeSecurityCheque;


    // Variables Used for FILE Dialog (CO-APPLICANT-1)

    RadioGroup rbGrpCoApp1, rbGrpCoApp1Photo, rbGrpCoApp1ResdProof, rbGrpCoApp1IDProof, rbGrpCoApp1SecurityCheque;
    EditText txtCoApp1ResdProofOthers, txtCoApp1IDProofOthers;
    RadioButton rbCoApp1, rbCoApp1Photo, rbCoApp1ResdProof, rbCoApp1IDProof, rbCoApp1SecurityCheque;
    RadioButton rbCoApp1Yes, rbCoApp1No, rbCoApp1PhotoYes, rbCoApp1PhotoNo, rbCoApp1ResdProofYes, rbCoApp1ResdProofNo, rbCoApp1IdProofYes, rbCoApp1IdProofNo, rbCoApp1SecurityChequeYes, rbCoApp1SecurityChequeNo;
    CheckBox chCoApp1ResdLightBill, chCoApp1ResdTaxBill, chCoApp1ResdAadharCard, chCoApp1ResdOther;
    CheckBox chCoApp1IDPanCard, chCoApp1IDElectionCard, chCoApp1IDAadharCard, chCoApp1IDDrivingLicence, chCoApp1IDOther;
    LinearLayout lvCoApp1Photo, lvCoApp1ResdProof, lvCoApp1ResdProofDetails, lvCoApp1IDProof, lvCoApp1IDProofDetails, lvCoApp1SecurityCheque;


    // Variables Used for FILE Dialog (CO-APPLICANT-2)

    RadioGroup rbGrpCoApp2, rbGrpCoApp2Photo, rbGrpCoApp2ResdProof, rbGrpCoApp2IDProof, rbGrpCoApp2SecurityCheque;
    EditText txtCoApp2ResdProofOthers, txtCoApp2IDProofOthers;
    RadioButton rbCoApp2, rbCoApp2Photo, rbCoApp2ResdProof, rbCoApp2IDProof, rbCoApp2SecurityCheque;
    RadioButton rbCoApp2Yes, rbCoApp2No, rbCoApp2PhotoYes, rbCoApp2PhotoNo, rbCoApp2ResdProofYes, rbCoApp2ResdProofNo, rbCoApp2IdProofYes, rbCoApp2IdProofNo, rbCoApp2SecurityChequeYes, rbCoApp2SecurityChequeNo;
    CheckBox chCoApp2ResdLightBill, chCoApp2ResdTaxBill, chCoApp2ResdAadharCard, chCoApp2ResdOther;
    CheckBox chCoApp2IDPanCard, chCoApp2IDElectionCard, chCoApp2IDAadharCard, chCoApp2IDDrivingLicence, chCoApp2IDOther;
    LinearLayout lvCoApp2Photo, lvCoApp2ResdProof, lvCoApp2ResdProofDetails, lvCoApp2IDProof, lvCoApp2IDProofDetails, lvCoApp2SecurityCheque;


    // Variables Used for SEIZE Dialog

    RadioGroup rbGrpPriorInfo, rbGrpPostInfo;
    RadioButton rbPriorInfoYes, rbPriorInfoNo, rbPostInfoYes, rbPostInfoNo, rbPriorInfoGiven, rbPostInfoGiven;
    Button btnSeizeSubmit;

    */
/*************************//*


    String cashAmount = "", bankAddress = "", bankName = "", chequeNo = "", coApp1Exist = "", coApp1IDProof = "", coApp1IDProofAdharCard = "", coApp1IDProofDrivingLicense = "", coApp1IDProofElectionCard = "",
            coApp1IDProofOther = "", coApp1IDProofOtherName = "", coApp1IDProofPanCard = "", coApp1Photo = "", coApp1ResiProof = "", coApp1ResiProofAdharCard = "", coApp1ResiProofLightBill = "", coApp1ResiProofOther = "",
            coApp1ResiProofOtherName = "", coApp1ResiProofTaxBill = "", coApp1SecurityCheque = "", coApp2Exist = "", coApp2IDProof = "", coApp2IDProofAdharCard = "", coApp2IDProofDrivingLicense = "", coApp2IDProofElectionCard = "",
            coApp2IDProofOther = "", coApp2IDProofOtherName = "", coApp2IDProofPanCard = "", coApp2Photo = "", coApp2ResiProof = "", coApp2ResiProofAdharCard = "", coApp2ResiProofLightBill = "", coApp2ResiProofOther = "",
            coApp2ResiProofOtherName = "", coApp2ResiProofTaxBill = "", coApp2SecurityCheque = "", collectionLatitude = "", collectionLongitude = "", ddNo = "", ifscCode = "", loaneeExist = "", loaneeIDProof = "", loaneeIDProofAdharCard = "", loaneeIDProofDrivingLicense = "", loaneeIDProofElectionCard = "",
            loaneeIDProofOther = "", loaneeIDProofOtherName = "", loaneeIDProofPanCard = "", loaneePhoto = "", loaneeResiProof = "", loaneeResiProofAdharCard = "", loaneeResiProofLightBill = "", loaneeResiProofOther = "",
            loaneeResiProofOtherName = "", loaneeResiProofTaxBill = "", loaneeSecurityCheque = "", micrCode = "", notes = "", paymentType = "", postInfoPolice = "", priorInfoPolice = "", remarkId = "", transactionDate = "",
            vehiclePhoto1 = "", vehiclePhoto2 = "", vehiclePhoto3 = "", bId = "0", cId = "0", sId = "0", status = "0", isActive = "1", isDelete = "0", isSync = "0", customerMobile = "";

    String ptpMode = "", ptpModeDate = "", ptpModeTime = "", ptpModeAmount = "", fileApprovalAmount = "", downPaymentAmount = "", fileLoanAmount = "", fileInstallmentNo = "", fileInstallmentAmount = "", calcLoanAmount = "", calcEmi = "", calcRoi = "", calcTenure = "",
            seizeVehicleName = "", seizeVehicleNo = "", seizeEngineNo = "", seizeChasisNo = "", seizePoliceStationName = "", seizePoliceStationCenter = "", seizingAgencyName = "", seizePoliceStationFax = "", seizePoliceStationEmail = "";

    double fApproveAmt = 0.0, fDpAmt = 0.0, fLoanAmt = 0.0;

    */
/*************************//*


    ArrayList<HashMap<String, String>> feedBackList, targetMasterList;

    NodeList name;
    Element line;

    // Creating Various Objects of SOAP Service Class
    UpdateDataService visitCollObj, feedBackObj, extraServiceObj, approvalMasterServiceObj;


    */
/*** Global Variable for SOAP Service Call (TARGET_MASTER) ***//*


    final String TRG_MAS_SERVICE = Config.SERVICE_URL + "TRGMasterService.svc";
    final String TRG_MASTER_COUNT_METHOD = "TrgmastCollectionFromSearchFieldsCount";
    final String TRG_MASTER_DETAILS_METHOD = "TrgmastCollectionFromSearchFields";
    final String OBJECT_NAME = "blTrgmast";
    final String TRG_ACTION1 = Config.TARGET_MAS_SOAP_ACTION + TRG_MASTER_COUNT_METHOD;
    final String TRG_ACTION2 = Config.TARGET_MAS_SOAP_ACTION + TRG_MASTER_DETAILS_METHOD;


    */
/*** Global Variable for SOAP Service Call (VISIT_COLLECTION) ***//*


    final String VISIT_COLLECTION_SERVICE = Config.SERVICE_URL + "VisitCollectionService.svc";
    final String VISIT_COLLECTION_COUNT = "VisitCollectionCollectionFromSearchFieldsCount";
    final String VISIT_COLLECTION_DETAILS = "VisitCollectionCollectionFromSearchFields";
    final String SAVE_NEW_VISIT_COLLECTION = "SaveNewVisitCollection";
    final String UPDATE_VISIT_COLLECTION = "UpdateVisitCollection";
    final String VISIT_COLLECTION_OBJ = "blVisitCollection";
    final String VISIT_COLLECTION_ACTION_1 = Config.VISIT_COLLECTION_SOAP_ACTION + VISIT_COLLECTION_COUNT;
    final String VISIT_COLLECTION_ACTION_2 = Config.VISIT_COLLECTION_SOAP_ACTION + VISIT_COLLECTION_DETAILS;
    final String VISIT_COLLECTION_ACTION_3 = Config.VISIT_COLLECTION_SOAP_ACTION + SAVE_NEW_VISIT_COLLECTION;
    final String VISIT_COLLECTION_ACTION_4 = Config.VISIT_COLLECTION_SOAP_ACTION + UPDATE_VISIT_COLLECTION;


    */
/*** Global Variable for SOAP Service Call (VISIT_COLLECTION_HISTORY) ***//*


    final String VISIT_COLLECTION_HISTORY_SERVICE   = Config.SERVICE_URL + "VisitCollectionHistoryService.svc";
    final String SAVE_NEW_VISIT_COLLECTION_HISTORY  = "SaveNewVisitCollectionHistory";
    final String VISIT_COLLECTION_HISTORY_OBJ       = "blVisitCollectionHistory";
    final String VISIT_COLLECTION_HISTORY_ACTION_1  = Config.VISIT_COLLECTION_HISTORY_SOAP_ACTION + SAVE_NEW_VISIT_COLLECTION_HISTORY;


    */
/*** Global Variable for SOAP Service Call (FEEDBACK) ***//*


    final String FEEDBACK_SERVICE = Config.SERVICE_URL + "FeedbackService.svc";
    final String FEEDBACK_COLLECTION = "FeedbackCollection";
    final String FEEDBACK_COLLECTION_COUNT = "FeedbackCollectionCount";
    final String FEEDBACK_OBJ = "blFeedback";
    final String FEEDBACK_ACTION_1 = Config.FEEDBACK_SOAP_ACTION + FEEDBACK_COLLECTION;
    final String FEEDBACK_ACTION_2 = Config.FEEDBACK_SOAP_ACTION + FEEDBACK_COLLECTION_COUNT;


    */
/*** Global Variable for SOAP Service Call (EXTRA_SERVICES) ***//*


    final String EXTRA_SERVICE = Config.SERVICE_URL + "Extraservices.svc";
    final String GET_PTP_COUNT_OF_CUSTOMER = "GetAppCustomerPTPCount";
    final String EXTRA_ACTION_1 = Config.EXTRA_SERVICE_SOAP_ACTION + GET_PTP_COUNT_OF_CUSTOMER;


    */
/*** Global Variable for SOAP Service Call (APPROVAL_MASTER) ***//*


    final String APPROVAL_MASTER_SERVICE = Config.SERVICE_URL + "ApprovalMasterService.svc";
    final String APPROVAL_MASTER_COUNT = "ApprovalMasterCollectionFromSearchFieldsCount";
    final String APPROVAL_MASTER_SAVE_DATA = "SaveNewApprovalMaster";
    final String APPROVAL_MASTER_UPDATE_DATA = "UpdateApprovalMaster";
    final String APPROVAL_MASTER_SEARCH_DATA = "ApprovalMasterCollectionFromSearchFields";
    final String APPROVAL_MASTER_OBJ = "blApprovalMaster";
    final String APPROVAL_MASTER_ACTION_1 = Config.APPROVAL_MASTER_SOAP_ACTION + APPROVAL_MASTER_COUNT;
    final String APPROVAL_MASTER_ACTION_2 = Config.APPROVAL_MASTER_SOAP_ACTION + APPROVAL_MASTER_SAVE_DATA;
    final String APPROVAL_MASTER_ACTION_3 = Config.APPROVAL_MASTER_SOAP_ACTION + APPROVAL_MASTER_SEARCH_DATA;
    final String APPROVAL_MASTER_ACTION_4 = Config.APPROVAL_MASTER_SOAP_ACTION + APPROVAL_MASTER_UPDATE_DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_visit_customer_details);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        feedBackList     = new ArrayList<HashMap<String, String>>();
        targetMasterList = new ArrayList<HashMap<String, String>>();

        dbAdapter = new DBAdapter(FieldVisitCustomerDetailsPlanned.this);

        // Get Values from Intent

        customerCode = getIntent().getStringExtra("CustCode");
        execCode     = getIntent().getStringExtra("ExecCode");
        custName     = getIntent().getStringExtra("CustName");
        trgMastId    = getIntent().getStringExtra("TrgMasterId");
        planVisitId  = getIntent().getStringExtra("PlanVisitId");
        userId       = getIntent().getStringExtra("UserId");
        ImeiNo       = getIntent().getStringExtra("ImeiNumber");
        isOnlineUser = getIntent().getStringExtra("UserAccess");

        if(!isOnlineUser.isEmpty() || !isOnlineUser.equals("null") || isOnlineUser != null)
        {
            userAccess = Integer.parseInt(isOnlineUser);
        }

        btnAction = (Button) findViewById(R.id.btnAction);
        btnAction.setOnClickListener(this);

        txtcustomerName = (TextView) findViewById(R.id.txtCustomerName);
        txtcustomerCode = (TextView) findViewById(R.id.txtCustomerCode);
        txtcustomerCode.setText(customerCode);
        txtcustomerName.setText(custName);


        // If USER ACCESS is ONLINE
        if(userAccess == 1)
        {
            if (checkNetwork.isInternetAvailable(this))
            {
                try
                {
                    new Download_CustomerData_OnLine().execute();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                DialogConfig.getInstance().ShowInternetDialog(FieldVisitCustomerDetailsPlanned.this);
            }
        }


        // If USER ACCESS is OFFLINE
        if(userAccess == 0)
        {
            try
            {
                new Download_CustomerData_OffLine().execute();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }


    */
/*** CLASS TO LOAD CUSTOMER DATA ( FOR ONLINE USERS)  ***//*


    private class Download_CustomerData_OnLine extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            DialogConfig.getInstance().StartProgressDialog(FieldVisitCustomerDetailsPlanned.this, Config.PLEASE_WAIT);
        }

        @Override
        protected Void doInBackground(Void... unused)
        {
            try
            {
                // Call Method to Load List of TARGET_MASTER Data
                SaveTrgMastData saveTrgMastData = new SaveTrgMastData(FieldVisitCustomerDetailsPlanned.this, execCode);
                targetMasterList = saveTrgMastData.GetTargetMasterData();

                // Call Method to Load Customer Details
                Load_Customer_Details();

                // Call function to fetch SETTING_MASTER data
                GetSettingMasterData();

                // Call Method to Download the Remark Data
                DownloadFeedBackData_ONLINE();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            DialogConfig.getInstance().StopProgressDialog();

            Bind_Customer_Data_To_Expandable_List();
        }
    }


    */
/*** CLASS TO LOAD CUSTOMER DATA ( FOR OFFLINE USERS)  ***//*


    private class Download_CustomerData_OffLine extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            DialogConfig.getInstance().StartProgressDialog(FieldVisitCustomerDetailsPlanned.this, Config.PLEASE_WAIT);
        }

        @Override
        protected Void doInBackground(Void... unused)
        {
            try
            {
                // Call Method to Load List of TARGET_MASTER Data
                SaveTrgMastData saveTrgMastData = new SaveTrgMastData(FieldVisitCustomerDetailsPlanned.this, execCode);
                targetMasterList = saveTrgMastData.GetTargetMasterData();

                // Call Method to Load Customer Details
                Load_Customer_Details();

                // Call function to fetch SETTING_MASTER data
                GetSettingMasterData();

                // Call Method to Download the Remark Data
                DownloadFeedBackData_OFFLINE();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            DialogConfig.getInstance().StopProgressDialog();

            Bind_Customer_Data_To_Expandable_List();
        }
    }


    */
/*** FETCH NECESSARY DATA FROM ARRAYLIST AND SQLITE DATABASE  ***//*


    public void Load_Customer_Details()
    {
        if(targetMasterList.size() != 0)
        {
            for(int i = 0 ; i < targetMasterList.size() ; i++)
            {
                HashMap<String, String> currentList = targetMasterList.get(i);

                String trgCustCode = currentList.get("CustCode");
                String trgExeCode = currentList.get("ExeCode");

                if(customerCode.equals(trgCustCode) && execCode.equals(trgExeCode))
                {
                    loanAmount              = currentList.get("LoanAmt");

                    loanDate                = currentList.get("LoanDate");
                    if(!loanDate.isEmpty() || !loanDate.equals("null") || loanDate != null)
                    {
                        loanDate = dateConversation(loanDate);
                    }

                    installmentDate         = currentList.get("InstDt");
                    if(!installmentDate.isEmpty() || !installmentDate.equals("null") || installmentDate != null)
                    {
                        installmentDate = dateConversation(installmentDate);
                    }

                    lastInstallmentDate     = currentList.get("LiDate");
                    if(!lastInstallmentDate.isEmpty() || !lastInstallmentDate.equals("null") || lastInstallmentDate != null)
                    {
                        lastInstallmentDate = dateConversation(lastInstallmentDate);
                    }

                    inst1Amount             = currentList.get("Inst1Amt");
                    noOfInstallment         = currentList.get("NoofInst");
                    segment                 = currentList.get("Segment");
                    itemName                = currentList.get("ItemName");
                    vehicleNo               = currentList.get("VehNo");
                    engineNo                = currentList.get("EngineNo");
                    chasisNo                = currentList.get("ChasisNo");
                    dealerName              = currentList.get("DealerName");
                    marketingExecutive      = currentList.get("MarketingExecutive");

                    returnCheque            = currentList.get("RtnChq");
                    odueInstallmentNo       = currentList.get("Odueinstno");
                    dueInstallmentAmt       = currentList.get("Dueinstamt");
                    approadAmount           = currentList.get("AprrodAmt");
                    capOs                   = currentList.get("CapOs");
                    charges                 = currentList.get("Charges");
                    customerType            = currentList.get("CustomerType");
                    caseRemarks             = currentList.get("CaseRemarks");
                    remarkOfReturnCaseForFastCollection = currentList.get("RemarksOfReturnForFastCollection");

                    chqDtM1                 = currentList.get("ChqdtM1");
                    if(!chqDtM1.isEmpty() || !chqDtM1.equals("null") || chqDtM1 != null)
                    {
                        chqDtM1 = dateConversation(chqDtM1);
                    }

                    chqDtM2                 = currentList.get("ChqdtM2");
                    if(!chqDtM2.isEmpty() || !chqDtM2.equals("null") || chqDtM2 != null)
                    {
                        chqDtM2 = dateConversation(chqDtM2);
                    }

                    chqDtM3                 = currentList.get("ChqdtM3");
                    if(!chqDtM3.isEmpty() || !chqDtM3.equals("null") || chqDtM3 != null)
                    {
                        chqDtM3 = dateConversation(chqDtM3);
                    }

                    chqNoM1                 = currentList.get("ChqnoM1");
                    chqNoM2                 = currentList.get("ChqnoM2");
                    chqNoM3                 = currentList.get("ChqnoM3");
                    chqRtnRsnM1             = currentList.get("RtnrsnM1");
                    chqRtnRsnM2             = currentList.get("RtnrsnM2");
                    chqRtnRsnM3             = currentList.get("RtnrsnM3");

                    legalCaseType            = currentList.get("CaseType");
                    stageCriminal            = currentList.get("StageCriminal");

                    caseFileDateCriminal     = currentList.get("CaseFileDateCriminal");
                    if(!caseFileDateCriminal.isEmpty() || !caseFileDateCriminal.equals("null") || caseFileDateCriminal != null)
                    {
                        caseFileDateCriminal = dateConversation(caseFileDateCriminal);
                    }

                    claimAmountCriminal      = currentList.get("ClaimAmtCriminal");

                    nextDateCriminal         = currentList.get("NextDateCriminal");
                    if(!nextDateCriminal.isEmpty() || !nextDateCriminal.equals("null") || nextDateCriminal != null)
                    {
                        nextDateCriminal = dateConversation(nextDateCriminal);
                    }

                    stageCivil               = currentList.get("StageCivil");

                    caseFileDateCivil        = currentList.get("CaseFileDateCivil");
                    if(!caseFileDateCivil.isEmpty() || !caseFileDateCivil.equals("null") || caseFileDateCivil != null)
                    {
                        caseFileDateCivil = dateConversation(caseFileDateCivil);
                    }

                    claimAmountCivil         = currentList.get("ClaimAmtCivil");

                    nextDateCivil            = currentList.get("NextDateCivil");
                    if(!nextDateCivil.isEmpty() || !nextDateCivil.equals("null") || nextDateCivil != null)
                    {
                        nextDateCivil = dateConversation(nextDateCivil);
                    }

                    stageArbitration         = currentList.get("NewStage");

                    caseFileDateArbitration  = currentList.get("ArbitrationDate");
                    if(!caseFileDateArbitration.isEmpty() || !caseFileDateArbitration.equals("null") || caseFileDateArbitration != null)
                    {
                        caseFileDateArbitration = dateConversation(caseFileDateArbitration);
                    }

                    arbitrationClaimAmount   = currentList.get("ArbitrationClaimAmount");
                }
            }
        }


        Cursor cursor1 = dbAdapter.FetchDataFromCustomerMasterByCustomerCode(customerCode);

        if(cursor1.getCount() == 1)
        {
            if (cursor1.moveToFirst())
            {
                do
                {
                    custName           = cursor1.getString(cursor1.getColumnIndex("CUST_NAME"));

                    custDateofBirth    = cursor1.getString(cursor1.getColumnIndex("BIRTH_DT"));
                    if(!custDateofBirth.isEmpty() || !custDateofBirth.equals("null") || custDateofBirth != null)
                    {
                        custDateofBirth = dateConversation(custDateofBirth);
                    }

                    custRblockNo       = cursor1.getString(cursor1.getColumnIndex("R_BLK_NO"));
                    custAdd1           = cursor1.getString(cursor1.getColumnIndex("R_ADD1"));
                    custAdd2           = cursor1.getString(cursor1.getColumnIndex("R_ADD2"));
                    custAdd3           = cursor1.getString(cursor1.getColumnIndex("R_ADD3"));
                    custCity           = cursor1.getString(cursor1.getColumnIndex("R_CITY"));
                    custPin            = cursor1.getString(cursor1.getColumnIndex("R_PIN"));
                    custRPhone         = cursor1.getString(cursor1.getColumnIndex("R_PHONE"));
                    custMPhone         = cursor1.getString(cursor1.getColumnIndex("M_PHONE"));

                    officeName         = cursor1.getString(cursor1.getColumnIndex("O_NAME"));
                    officeAdd1         = cursor1.getString(cursor1.getColumnIndex("O_ADD1"));
                    officeAdd2         = cursor1.getString(cursor1.getColumnIndex("O_ADD2"));
                    officeAdd3         = cursor1.getString(cursor1.getColumnIndex("O_ADD3"));
                    officeCity         = cursor1.getString(cursor1.getColumnIndex("O_CITY"));
                    officePin          = cursor1.getString(cursor1.getColumnIndex("O_PIN"));
                    officeRPhone       = cursor1.getString(cursor1.getColumnIndex("O_PHONE"));
                }
                while (cursor1.moveToNext());
            }
        }


        Cursor cursor2 = dbAdapter.FetchDataFromGuarantorMasterByCustomerCode(customerCode);

        if(cursor2.getCount() == 1)
        {
            if (cursor2.moveToFirst())
            {
                do
                {
                    g1Name             = cursor2.getString(cursor2.getColumnIndex("G_NAME"));
                    g1BlockNo          = cursor2.getString(cursor2.getColumnIndex("G_R_ADD1"));
                    g1Add1             = cursor2.getString(cursor2.getColumnIndex("G_R_ADD2"));
                    g1Add2             = cursor2.getString(cursor2.getColumnIndex("G_R_ADD3"));
                    g1Add3             = cursor2.getString(cursor2.getColumnIndex("G_R_LAND"));
                    g1City             = cursor2.getString(cursor2.getColumnIndex("G_R_CITY"));
                    g1Pin              = cursor2.getString(cursor2.getColumnIndex("G_R_PIN"));
                    g1RPhone           = cursor2.getString(cursor2.getColumnIndex("G_R_PHONE"));

                    g2Name             = cursor2.getString(cursor2.getColumnIndex("G2_NAME"));
                    g2BlockNo          = cursor2.getString(cursor2.getColumnIndex("G2_R_ADD1"));
                    g2Add1             = cursor2.getString(cursor2.getColumnIndex("G2_R_ADD2"));
                    g2Add2             = cursor2.getString(cursor2.getColumnIndex("G2_R_ADD3"));
                    g2Add3             = cursor2.getString(cursor2.getColumnIndex("G2_R_LAND"));
                    g2City             = cursor2.getString(cursor2.getColumnIndex("G2_R_CITY"));
                    g2Pin              = cursor2.getString(cursor2.getColumnIndex("G2_R_PIN"));
                    g2RPhone           = cursor2.getString(cursor2.getColumnIndex("G2_R_PHONE"));
                }
                while (cursor2.moveToNext());
            }
        }
    }


    */
/*** FETCH FEEDBACK_MASTER DATA ( ONLINE USERS )  ***//*


    public void DownloadFeedBackData_ONLINE()
    {
        try
        {
            */
/****** LOAD REMARK DATA FORM REMARK TABLE FROM RMS DATABASE FROM SERVER *******//*


            try
            {
                feedBackObj = new UpdateDataService(FEEDBACK_SERVICE);

                feedBackObj.CallServiceObject(FEEDBACK_ACTION_2, FEEDBACK_COLLECTION_COUNT, FEEDBACK_OBJ);

                feedBackCnt = Integer.parseInt((String) feedBackObj.getVariableValue("FeedbackCollectionCountResult", String.class));

                if(feedBackCnt > 0)
                {
                    feedBackObj.CallServiceObject(FEEDBACK_ACTION_1, FEEDBACK_COLLECTION, FEEDBACK_OBJ);

                    feedBackResponse = feedBackObj.getResult();

                    // Code to Parse the XML Response

                    DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(feedBackResponse));

                    Document doc = db.parse(is);
                    NodeList nodes = doc.getElementsByTagName("a:BLFeedback");

                    feedBackList.clear();

                    for (int i = 0; i < nodes.getLength(); i++)
                    {
                        HashMap<String, String> map = new HashMap<String, String>();
                        Element element = (Element) nodes.item(i);

                        name = element.getElementsByTagName("a:Id");
                        line = (Element) name.item(0);
                        feedBackId = getCharacterDataFromElement(line);
                        map.put("Id", feedBackId);

                        name = element.getElementsByTagName("a:Feedback");
                        line = (Element) name.item(0);
                        feedBackName = getCharacterDataFromElement(line);
                        map.put("Feedback", feedBackName);

                        name = element.getElementsByTagName("a:Exist");
                        line = (Element) name.item(0);
                        feedBackExist = getCharacterDataFromElement(line);
                        map.put("Exist", feedBackExist);

                        feedBackList.add(map);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/*** FETCH FEEDBACK_MASTER DATA ( OFFLINE USERS )  ***//*


    public void DownloadFeedBackData_OFFLINE()
    {
        try
        {
            Cursor cursor = dbAdapter.fetchAllFeedbackData();

            if(cursor.getCount() > 0)
            {
                feedBackList.clear();

                if (cursor.moveToFirst())
                {
                    do
                    {
                        HashMap<String, String> map = new HashMap<String, String>();

                        String fId     = cursor.getString(cursor.getColumnIndex("ID"));
                        String fName   = cursor.getString(cursor.getColumnIndex("FEEDBACK"));
                        String fExist  = cursor.getString(cursor.getColumnIndex("EXIST"));

                        map.put("Id", fId);
                        map.put("Feedback", fName);
                        map.put("Exist", fExist);

                        feedBackList.add(map);
                    }
                    while (cursor.moveToNext());
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/*** BIND DATA TO EXPANDABLE LIST VIEW ***//*


    public void Bind_Customer_Data_To_Expandable_List()
    {
        try
        {
            expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

            expandableListDetail = ExpandableDataListLoader.SetCustomerData(custName, custDateofBirth, custRblockNo, custAdd1, custAdd2, custAdd3, custCity, custPin, custRPhone, custMPhone,
                                                                            officeName, officeAdd1, officeAdd2, officeAdd3, officeCity, officePin, officeRPhone,
                                                                            g1Name, g1BlockNo, g1Add1, g1Add2, g1Add3, g1City, g1Pin, g1RPhone,
                                                                            g2Name, g2BlockNo, g2Add1, g2Add2, g2Add3, g2City, g2Pin, g2RPhone,
                                                                            loanAmount, loanDate, installmentDate, lastInstallmentDate, inst1Amount, noOfInstallment, segment, itemName, vehicleNo, engineNo, chasisNo, dealerName, marketingExecutive,
                                                                            returnCheque, odueInstallmentNo, dueInstallmentAmt, approadAmount, capOs, charges, customerType, caseRemarks, remarkOfReturnCaseForFastCollection,
                                                                            chqDtM1, chqNoM1, chqRtnRsnM1, chqDtM2, chqNoM2, chqRtnRsnM2, chqDtM3, chqNoM3, chqRtnRsnM3,
                                                                            legalCaseType, stageCriminal, caseFileDateCriminal, claimAmountCriminal, nextDateCriminal, stageCivil, caseFileDateCivil, claimAmountCivil, nextDateCivil, stageArbitration, caseFileDateArbitration, arbitrationClaimAmount);

            expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

            expandableListAdapter = new CustomExpandableListAdapter(this, expandableListTitle, expandableListDetail);

            expandableListView.setAdapter(expandableListAdapter);


            expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener()
            {
                @Override
                public void onGroupExpand(int groupPosition) {}
            });

            expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener()
            {
                @Override
                public void onGroupCollapse(int groupPosition) {}
            });

            expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
            {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
                {
                    return false;
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/* FUNCTION TO CONVERT SOAP FORMAT DATE TO DD-MM-YYYY FORMAT*//*


    public String dateConversation(String inputDate)
    {
        String outputDate = "";

        try
        {
            Date convertedDate = targetFormat.parse(inputDate);
            outputDate         = originalFormat.format(convertedDate);

            return outputDate;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return outputDate;
    }


    */
/*** METHOD TO FETCH SETTING_MASTER DATA FROM SQLITE ***//*


    public void GetSettingMasterData()
    {
        Cursor c1 = dbAdapter.getSettingMasterData();

        if(c1 != null)
        {
            if (c1.getCount() > 0)
            {

                if (c1.moveToFirst())
                {
                    do
                    {
                        MAX_PTP_ALLOWED = c1.getString(c1.getColumnIndex("MAX_PTP_ALLOWED_PER_MONTH"));
                    }
                    while (c1.moveToNext());

                    // Check for NULL and Cast it to INTEGER Value
                    if(!MAX_PTP_ALLOWED.isEmpty() && MAX_PTP_ALLOWED != null && !MAX_PTP_ALLOWED.equals("null"))
                    {
                        maxPTP = Integer.parseInt(MAX_PTP_ALLOWED);
                    }
                }
            }
        }
    }


    @Override
    public void onClick(View view)
    {
        if(view == btnAction)
        {
            new AlertDialog.Builder(this)

                    .setItems(actions, new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int position)
                        {
                            switch (position)
                            {
                                */
/***** CASE 0 FOR COLLECTION MODE *****//*


                                case 0:

                                    // IF USER ACCESS IS ONLINE
                                    if(userAccess == 1)
                                    {
                                        if (checkNetwork.isInternetAvailable(getApplicationContext()))
                                        {
                                            ExecuteCollectionModeOperation();
                                        }
                                        else
                                        {
                                            DialogConfig.getInstance().ShowInternetDialog(FieldVisitCustomerDetailsPlanned.this);
                                        }
                                    }

                                    // IF USER ACCESS IS OFFLINE
                                    if(userAccess == 0)
                                    {
                                        ExecuteCollectionModeOperation();
                                    }

                                    break;


                                */
/***** CASE 1 FOR PTP (PROMISE TO PAY) MODE *****//*


                                case 1:

                                    // IF USER ACCESS IS ONLINE
                                    if (userAccess == 1)
                                    {
                                        if (checkNetwork.isInternetAvailable(getApplicationContext()))
                                        {
                                            // Call Method to Open PTP Mode Dialog
                                            DisplayPtpDialog();
                                        }
                                        else
                                        {
                                            DialogConfig.getInstance().ShowInternetDialog(FieldVisitCustomerDetailsPlanned.this);
                                        }
                                    }

                                    // IF USER ACCESS IS OFFLINE
                                    if (userAccess == 0)
                                    {
                                        // Call Method to Open PTP Mode Dialog
                                        DisplayPtpDialog();
                                    }

                                    break;


                                */
/***** CASE 2 FOR SEIZE MODE *****//*


                                case 2:

                                    // IF USER ACCESS IS ONLINE
                                    if (userAccess == 1)
                                    {
                                        if (checkNetwork.isInternetAvailable(getApplicationContext()))
                                        {
                                            // Call Method to Open Seize Mode Dialog
                                            DisplaySeizeDialog();
                                        }
                                        else
                                        {
                                            DialogConfig.getInstance().ShowInternetDialog(FieldVisitCustomerDetailsPlanned.this);
                                        }
                                    }

                                    // IF USER ACCESS IS OFFLINE
                                    if (userAccess == 0)
                                    {
                                        // Call Method to Open Seize Mode Dialog
                                        DisplaySeizeDialog();
                                    }

                                    break;


                                */
/***** CASE 3 FOR FILE MODE *****//*


                                case 3:

                                    // IF USER ACCESS IS ONLINE
                                    if (userAccess == 1)
                                    {
                                        if (checkNetwork.isInternetAvailable(getApplicationContext()))
                                        {
                                            // Call Method to Open File Mode Dialog
                                            DisplayFileDialog();
                                        }
                                        else
                                        {
                                            DialogConfig.getInstance().ShowInternetDialog(FieldVisitCustomerDetailsPlanned.this);
                                        }
                                    }

                                    // IF USER ACCESS IS OFFLINE
                                    if (userAccess == 0)
                                    {
                                        // Call Method to Open File Mode Dialog
                                        DisplayFileDialog();
                                    }

                                    break;


                                */
/***** CASE 4 FOR REMARK MODE *****//*


                                case 4:

                                    // IF USER ACCESS IS ONLINE
                                    if (userAccess == 1)
                                    {
                                        if (checkNetwork.isInternetAvailable(getApplicationContext()))
                                        {
                                            // Call Method to Open Remark Mode Dialog
                                            DisplayRemarkDialog();
                                        }
                                        else
                                        {
                                            DialogConfig.getInstance().ShowInternetDialog(FieldVisitCustomerDetailsPlanned.this);
                                        }
                                    }

                                    // IF USER ACCESS IS OFFLINE
                                    if (userAccess == 0)
                                    {
                                        // Call Method to Open Remark Mode Dialog
                                        DisplayRemarkDialog();
                                    }

                                    break;
                            }
                        }
                    })
                    .show();
        }
    }


    */
/*** METHOD TO SHOW PTP DIALOG ***//*


    public void DisplayPtpDialog()
    {
        dialogPtpMode = new Dialog(FieldVisitCustomerDetailsPlanned.this);
        dialogPtpMode.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogPtpMode.setContentView(R.layout.dialog_new_ptp_mode);
        dialogPtpMode.setCancelable(true);

        lvCashDate           = (LinearLayout) dialogPtpMode.findViewById(R.id.lvCashDate);
        lvCashTime           = (LinearLayout) dialogPtpMode.findViewById(R.id.lvCashTime);
        lvCashAmount         = (LinearLayout) dialogPtpMode.findViewById(R.id.lvCashAmount);
        lvFileDate           = (LinearLayout) dialogPtpMode.findViewById(R.id.lvFileDate);
        lvFileApproveAmt     = (LinearLayout) dialogPtpMode.findViewById(R.id.lvFileApproveAmount);
        lvFileDpAmt          = (LinearLayout) dialogPtpMode.findViewById(R.id.lvFileDpAmount);
        lvFileLoanAmt        = (LinearLayout) dialogPtpMode.findViewById(R.id.lvFileLoanAmount);
        lvFileInstallmentNo  = (LinearLayout) dialogPtpMode.findViewById(R.id.lvFileInstallmentNo);
        lvFileInstallmentAmt = (LinearLayout) dialogPtpMode.findViewById(R.id.lvFileInstallmentAmount);
        lvLoanCalcTitle      = (LinearLayout) dialogPtpMode.findViewById(R.id.lvLoanCalcTitle);
        lvLoanCalcSelection  = (LinearLayout) dialogPtpMode.findViewById(R.id.lvLoanCalcSelection);
        lvLoanAmount         = (LinearLayout) dialogPtpMode.findViewById(R.id.lvLoanCalcLoanAmount);
        lvLoanEmi            = (LinearLayout) dialogPtpMode.findViewById(R.id.lvLoanCalcEmi);
        lvLoanRoi            = (LinearLayout) dialogPtpMode.findViewById(R.id.lvLoanCalcRoi);
        lvLoanTenure         = (LinearLayout) dialogPtpMode.findViewById(R.id.lvLoanCalcTenure);
        lvLoanCalculate      = (LinearLayout) dialogPtpMode.findViewById(R.id.lvFileBtnCalculate);
        lvSeizeDate          = (LinearLayout) dialogPtpMode.findViewById(R.id.lvSeizeDate);
        lvVehicleName        = (LinearLayout) dialogPtpMode.findViewById(R.id.lvSeizeVehicleName);
        lvVehicleNo          = (LinearLayout) dialogPtpMode.findViewById(R.id.lvSeizeVehicleNo);
        lvChasisNo           = (LinearLayout) dialogPtpMode.findViewById(R.id.lvSeizeChasisNo);
        lvEngineNo           = (LinearLayout) dialogPtpMode.findViewById(R.id.lvSeizeEngineNo);
        lvPsName             = (LinearLayout) dialogPtpMode.findViewById(R.id.lvSeizePSName);
        lvPsCenter           = (LinearLayout) dialogPtpMode.findViewById(R.id.lvSeizePSCenter);
        lvAgencyName         = (LinearLayout) dialogPtpMode.findViewById(R.id.lvSeizeAgencyName);
        lvPsFaxNo            = (LinearLayout) dialogPtpMode.findViewById(R.id.lvSeizeFaxNo);
        lvPsEmail            = (LinearLayout) dialogPtpMode.findViewById(R.id.lvSeizeEmailId);


        txtCashPtpDate           = (EditText) dialogPtpMode.findViewById(R.id.txtPtpCashDate);
        txtCashPtpTime           = (EditText) dialogPtpMode.findViewById(R.id.txtPtpCashTime);
        txtCashPtpAmount         = (EditText) dialogPtpMode.findViewById(R.id.txtPtpCashAmount);
        txtFilePtpDate           = (EditText) dialogPtpMode.findViewById(R.id.txtPtpFileDate);
        txtFileApprovalAmount    = (EditText) dialogPtpMode.findViewById(R.id.txtFileApproveAmount);
        txtFileDpAmount          = (EditText) dialogPtpMode.findViewById(R.id.txtDownPaymentAmount);
        txtFileLoanAmount        = (EditText) dialogPtpMode.findViewById(R.id.txtFileLoanAmount);
        txtFileNoOfInstallment   = (EditText) dialogPtpMode.findViewById(R.id.txtFileInstallmentNo);
        txtFileInstallmentAmount = (EditText) dialogPtpMode.findViewById(R.id.txtFileInstallmentAmount);
        txtLoanAmount            = (EditText) dialogPtpMode.findViewById(R.id.txtLoanAmount);
        txtEmi                   = (EditText) dialogPtpMode.findViewById(R.id.txtEmi);
        txtRoi                   = (EditText) dialogPtpMode.findViewById(R.id.txtRoi);
        txtTenure                = (EditText) dialogPtpMode.findViewById(R.id.txtTenure);
        txtSeizePtpDate          = (EditText) dialogPtpMode.findViewById(R.id.txtPtpSeizeDate);
        txtVehicleName           = (EditText) dialogPtpMode.findViewById(R.id.txtVehicleName);
        txtVehicleNo             = (EditText) dialogPtpMode.findViewById(R.id.txtVehicleNo);
        txtChasisNo              = (EditText) dialogPtpMode.findViewById(R.id.txtChasisNo);
        txtEngineNo              = (EditText) dialogPtpMode.findViewById(R.id.txtEngineNo);
        txtPoliceStationName     = (EditText) dialogPtpMode.findViewById(R.id.txtNameofPoliceStation);
        txtPolicStationCenter    = (EditText) dialogPtpMode.findViewById(R.id.txtCenterNamePoliceStation);
        txtSeizeAgencyName       = (EditText) dialogPtpMode.findViewById(R.id.txtSeizingAgencyName);
        txtPoliceStationFAX      = (EditText) dialogPtpMode.findViewById(R.id.txtFaxNo);
        txtPoliceStationEmail    = (EditText) dialogPtpMode.findViewById(R.id.txtPoliceStationEmail);
        txtCustomerMobile        = (EditText) dialogPtpMode.findViewById(R.id.txtCustomerMobile);

        rvPtpCashDatePicker      = (RelativeLayout) dialogPtpMode.findViewById(R.id.rvPtpCashDatePick);
        rvPtpCashTimePicker      = (RelativeLayout) dialogPtpMode.findViewById(R.id.rvPtpCashTimePick);
        rvPtpFileDatePicker      = (RelativeLayout) dialogPtpMode.findViewById(R.id.rvPtpFileDatePick);
        rvPtpSeizeDatePicker     = (RelativeLayout) dialogPtpMode.findViewById(R.id.rvPtpSeizeDatePick);

        rbGrpPtpMode     = (RadioGroup) dialogPtpMode.findViewById(R.id.rbGrpPtpMode);
        rbGrpLoanCalc    = (RadioGroup) dialogPtpMode.findViewById(R.id.rbGrpLoanCalc);

        rbPtpCash        = (RadioButton) dialogPtpMode.findViewById(R.id.rbPtpCash);
        rbPtpFile        = (RadioButton) dialogPtpMode.findViewById(R.id.rbPtpFile);
        rbPtpSeize       = (RadioButton) dialogPtpMode.findViewById(R.id.rbPtpSeize);

        rbLoanAmt        = (RadioButton) dialogPtpMode.findViewById(R.id.rbLoanAmt);
        rbEmi            = (RadioButton) dialogPtpMode.findViewById(R.id.rbEmi);
        rbRoi            = (RadioButton) dialogPtpMode.findViewById(R.id.rbRoi);
        rbTenure         = (RadioButton) dialogPtpMode.findViewById(R.id.rbTenure);

        btnPTPLoanCalculate  = (Button) dialogPtpMode.findViewById(R.id.btnLoanCalculator);
        btnPtpSubmit         = (Button) dialogPtpMode.findViewById(R.id.btnPtpSubmit);

        int rbPtpModeSelected = rbGrpPtpMode.getCheckedRadioButtonId();
        rbPtpMode = (RadioButton) dialogPtpMode.findViewById(rbPtpModeSelected);


        rbGrpPtpMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        // IF PTP MODE = CASH
                        case R.id.rbPtpCash:

                            lvCashDate.setVisibility(View.VISIBLE);
                            lvCashTime.setVisibility(View.VISIBLE);
                            lvCashAmount.setVisibility(View.VISIBLE);

                            lvFileDate.setVisibility(View.GONE);
                            lvFileApproveAmt.setVisibility(View.GONE);
                            lvFileDpAmt.setVisibility(View.GONE);
                            lvFileLoanAmt.setVisibility(View.GONE);
                            lvFileInstallmentNo.setVisibility(View.GONE);
                            lvFileInstallmentAmt.setVisibility(View.GONE);
                            lvLoanCalcTitle.setVisibility(View.GONE);
                            lvLoanCalcSelection.setVisibility(View.GONE);
                            lvLoanAmount.setVisibility(View.GONE);
                            lvLoanEmi.setVisibility(View.GONE);
                            lvLoanRoi.setVisibility(View.GONE);
                            lvLoanTenure.setVisibility(View.GONE);
                            lvLoanCalculate.setVisibility(View.GONE);

                            lvSeizeDate.setVisibility(View.GONE);
                            lvVehicleName.setVisibility(View.GONE);
                            lvVehicleNo.setVisibility(View.GONE);
                            lvChasisNo.setVisibility(View.GONE);
                            lvEngineNo.setVisibility(View.GONE);
                            lvPsName.setVisibility(View.GONE);
                            lvPsCenter.setVisibility(View.GONE);
                            lvAgencyName.setVisibility(View.GONE);
                            lvPsFaxNo.setVisibility(View.GONE);
                            lvPsEmail.setVisibility(View.GONE);

                            // Empty Text Box Values
                            EmptyPtpDialogTextValues();

                            break;


                        // IF PTP MODE = FILE
                        case R.id.rbPtpFile:

                            lvCashDate.setVisibility(View.GONE);
                            lvCashTime.setVisibility(View.GONE);
                            lvCashAmount.setVisibility(View.GONE);

                            lvFileDate.setVisibility(View.VISIBLE);
                            lvFileApproveAmt.setVisibility(View.VISIBLE);
                            lvFileDpAmt.setVisibility(View.VISIBLE);
                            lvFileLoanAmt.setVisibility(View.VISIBLE);
                            lvFileInstallmentNo.setVisibility(View.VISIBLE);
                            lvFileInstallmentAmt.setVisibility(View.VISIBLE);
                            lvLoanCalcTitle.setVisibility(View.VISIBLE);
                            lvLoanCalcSelection.setVisibility(View.VISIBLE);
                            lvLoanAmount.setVisibility(View.VISIBLE);
                            lvLoanEmi.setVisibility(View.VISIBLE);
                            lvLoanRoi.setVisibility(View.VISIBLE);
                            lvLoanTenure.setVisibility(View.VISIBLE);
                            lvLoanCalculate.setVisibility(View.VISIBLE);

                            lvSeizeDate.setVisibility(View.GONE);
                            lvVehicleName.setVisibility(View.GONE);
                            lvVehicleNo.setVisibility(View.GONE);
                            lvChasisNo.setVisibility(View.GONE);
                            lvEngineNo.setVisibility(View.GONE);
                            lvPsName.setVisibility(View.GONE);
                            lvPsCenter.setVisibility(View.GONE);
                            lvAgencyName.setVisibility(View.GONE);
                            lvPsFaxNo.setVisibility(View.GONE);
                            lvPsEmail.setVisibility(View.GONE);

                            // Empty Text Box Values
                            EmptyPtpDialogTextValues();

                            txtFileApprovalAmount.requestFocus();

                            break;


                        // IF PTP MODE = SEIZE
                        case R.id.rbPtpSeize:

                            lvCashDate.setVisibility(View.GONE);
                            lvCashTime.setVisibility(View.GONE);
                            lvCashAmount.setVisibility(View.GONE);

                            lvFileDate.setVisibility(View.GONE);
                            lvFileApproveAmt.setVisibility(View.GONE);
                            lvFileDpAmt.setVisibility(View.GONE);
                            lvFileLoanAmt.setVisibility(View.GONE);
                            lvFileInstallmentNo.setVisibility(View.GONE);
                            lvFileInstallmentAmt.setVisibility(View.GONE);
                            lvLoanCalcTitle.setVisibility(View.GONE);
                            lvLoanCalcSelection.setVisibility(View.GONE);
                            lvLoanAmount.setVisibility(View.GONE);
                            lvLoanEmi.setVisibility(View.GONE);
                            lvLoanRoi.setVisibility(View.GONE);
                            lvLoanTenure.setVisibility(View.GONE);
                            lvLoanCalculate.setVisibility(View.GONE);

                            lvSeizeDate.setVisibility(View.VISIBLE);
                            lvVehicleName.setVisibility(View.VISIBLE);
                            lvVehicleNo.setVisibility(View.VISIBLE);
                            lvChasisNo.setVisibility(View.VISIBLE);
                            lvEngineNo.setVisibility(View.VISIBLE);
                            lvPsName.setVisibility(View.VISIBLE);
                            lvPsCenter.setVisibility(View.VISIBLE);
                            lvAgencyName.setVisibility(View.VISIBLE);
                            lvPsFaxNo.setVisibility(View.VISIBLE);
                            lvPsEmail.setVisibility(View.VISIBLE);

                            // Empty Text Box Values
                            EmptyPtpDialogTextValues();

                            txtVehicleName.requestFocus();

                            break;
                    }
                }
            }
        });


        rvPtpCashTimePicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Calendar mcurrentTime = Calendar.getInstance();

                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(FieldVisitCustomerDetailsPlanned.this, R.style.CustomCalendar, new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
                    {
                        String msg = DisplayDateTime(selectedHour, selectedMinute);
                        txtCashPtpTime.setText(msg);
                    }
                }, hour, minute, false);  // Yes 24 hour time

                mTimePicker.show();
            }
        });


        rvPtpCashDatePicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog(0);
            }
        });


        rvPtpFileDatePicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog(1);
            }
        });


        rvPtpSeizeDatePicker.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog(2);
            }
        });


        */
/*** FOR LOAN CALCULATOR ***//*


        int rbLoanCalcSelected = rbGrpLoanCalc.getCheckedRadioButtonId();
        rbLoanCalcType = (RadioButton) dialogPtpMode.findViewById(rbLoanCalcSelected);

        if(rbLoanCalcType == rbLoanAmt)
        {
            txtLoanAmount.setEnabled(false);
            txtEmi.setEnabled(true);
            txtRoi.setEnabled(true);
            txtTenure.setEnabled(true);

            txtLoanAmount.setBackgroundResource(R.drawable.disabledittext);
            txtEmi.setBackgroundResource(R.drawable.customedittext);
            txtRoi.setBackgroundResource(R.drawable.customedittext);
            txtTenure.setBackgroundResource(R.drawable.customedittext);

            txtEmi.requestFocus();
            emptyLoanCalcTextValue();
        }


        rbGrpLoanCalc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        case R.id.rbLoanAmt:

                            txtLoanAmount.setEnabled(false);
                            txtEmi.setEnabled(true);
                            txtRoi.setEnabled(true);
                            txtTenure.setEnabled(true);

                            txtLoanAmount.setBackgroundResource(R.drawable.disabledittext);
                            txtEmi.setBackgroundResource(R.drawable.customedittext);
                            txtRoi.setBackgroundResource(R.drawable.customedittext);
                            txtTenure.setBackgroundResource(R.drawable.customedittext);

                            txtEmi.requestFocus();
                            emptyLoanCalcTextValue();

                            break;


                        case R.id.rbEmi:

                            txtLoanAmount.setEnabled(true);
                            txtEmi.setEnabled(false);
                            txtRoi.setEnabled(true);
                            txtTenure.setEnabled(true);

                            txtLoanAmount.setBackgroundResource(R.drawable.customedittext);
                            txtEmi.setBackgroundResource(R.drawable.disabledittext);
                            txtRoi.setBackgroundResource(R.drawable.customedittext);
                            txtTenure.setBackgroundResource(R.drawable.customedittext);

                            txtLoanAmount.requestFocus();
                            emptyLoanCalcTextValue();

                            break;


                        case R.id.rbRoi:

                            txtLoanAmount.setEnabled(true);
                            txtEmi.setEnabled(true);
                            txtRoi.setEnabled(false);
                            txtTenure.setEnabled(true);

                            txtLoanAmount.setBackgroundResource(R.drawable.customedittext);
                            txtEmi.setBackgroundResource(R.drawable.customedittext);
                            txtRoi.setBackgroundResource(R.drawable.disabledittext);
                            txtTenure.setBackgroundResource(R.drawable.customedittext);

                            txtLoanAmount.requestFocus();
                            emptyLoanCalcTextValue();

                            break;


                        case R.id.rbTenure:

                            txtLoanAmount.setEnabled(true);
                            txtEmi.setEnabled(true);
                            txtRoi.setEnabled(true);
                            txtTenure.setEnabled(false);

                            txtLoanAmount.setBackgroundResource(R.drawable.customedittext);
                            txtEmi.setBackgroundResource(R.drawable.customedittext);
                            txtRoi.setBackgroundResource(R.drawable.customedittext);
                            txtTenure.setBackgroundResource(R.drawable.disabledittext);

                            txtLoanAmount.requestFocus();
                            emptyLoanCalcTextValue();

                            break;
                    }
                }
            }
        });


        */
/*** METHOD TO PERFORM ONCLICK EVENT OF CALCULATE BUTTON IN FILE MODE OPERATION ***//*


        btnPTPLoanCalculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    if(rbPtpFile.isChecked())
                    {
                        calcLoanAmount = txtLoanAmount.getText().toString().trim();
                        calcEmi        = txtEmi.getText().toString().trim();
                        calcRoi        = txtRoi.getText().toString().trim();
                        calcTenure     = txtTenure.getText().toString().trim();

                        // If Loan Amount is Checked
                        if(rbLoanAmt.isChecked())
                        {
                            if(calcEmi.isEmpty())
                            {
                                txtEmi.requestFocus();
                                txtEmi.setError("Enter EMI Value");
                            }
                            else if(calcRoi.isEmpty())
                            {
                                txtRoi.requestFocus();
                                txtRoi.setError("Enter Rate of Interest");
                            }
                            else if(calcTenure.isEmpty())
                            {
                                txtTenure.requestFocus();
                                txtTenure.setError("Enter Tenure");
                            }
                            else
                            {
                                ExecuteCalculateOperation(1);
                            }
                        }

                        // If EMI is Checked
                        if(rbEmi.isChecked())
                        {
                            if(calcLoanAmount.isEmpty())
                            {
                                txtLoanAmount.requestFocus();
                                txtLoanAmount.setError("Enter Loan Amount");
                            }
                            else if(calcRoi.isEmpty())
                            {
                                txtRoi.requestFocus();
                                txtRoi.setError("Enter Rate of Interest");
                            }
                            else if(calcTenure.isEmpty())
                            {
                                txtTenure.requestFocus();
                                txtTenure.setError("Enter Tenure");
                            }
                            else
                            {
                                ExecuteCalculateOperation(2);
                            }
                        }

                        // If ROI is Checked
                        if(rbRoi.isChecked())
                        {
                            if(calcLoanAmount.isEmpty())
                            {
                                txtLoanAmount.requestFocus();
                                txtLoanAmount.setError("Enter Loan Amount");
                            }
                            else if(calcEmi.isEmpty())
                            {
                                txtEmi.requestFocus();
                                txtEmi.setError("Enter EMI Value");
                            }
                            else if(calcTenure.isEmpty())
                            {
                                txtTenure.requestFocus();
                                txtTenure.setError("Enter Tenure");
                            }
                            else
                            {
                                ExecuteCalculateOperation(3);
                            }
                        }

                        // If Tenure is Checked
                        if(rbTenure.isChecked())
                        {
                            if(calcLoanAmount.isEmpty())
                            {
                                txtLoanAmount.requestFocus();
                                txtLoanAmount.setError("Enter Loan Amount");
                            }
                            else if(calcEmi.isEmpty())
                            {
                                txtEmi.requestFocus();
                                txtEmi.setError("Enter EMI Value");
                            }
                            else if(calcRoi.isEmpty())
                            {
                                txtRoi.requestFocus();
                                txtRoi.setError("Enter Rate of Interest");
                            }
                            else
                            {
                                ExecuteCalculateOperation(4);
                            }
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


        */
/*** METHOD TO PERFORM ONCLICK EVENT OF SUBMIT BUTTON IN FILE MODE OPERATION ***//*


        btnPtpSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    CheckForPTPValidation();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


        // Call Method to Execute Loan Amount Calculation
        ExecuteLoanAmountCalculation();


        dialogPtpMode.show();
        Window window = dialogPtpMode.getWindow();
        assert window != null;
        window.setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
    }


    */
/*** CHECK PTP VALIDATION & EXECUTE PTP OPERATION ***//*


    public void CheckForPTPValidation()
    {
        try
        {
            // Set Visit Type = PTP
            visitTypeActual = Config.PTP_VISIT;

            if(rbPtpCash.isChecked())
            {
                //Set PTP Mode = CASH MODE
                ptpMode = Config.PTP_CASH;


                // Get Values from the TextBox
                ptpModeDate    = txtCashPtpDate.getText().toString().trim();
                ptpModeTime    = txtCashPtpTime.getText().toString().trim();
                ptpModeAmount  = txtCashPtpAmount.getText().toString().trim();
                customerMobile = txtCustomerMobile.getText().toString().trim();

                // Check for Validation
                if(ptpModeDate.isEmpty())
                {
                    DialogConfig.getInstance().DisplayCustomToastMsg(FieldVisitCustomerDetailsPlanned.this, "Select PTP Date");
                }
                else if(ptpModeTime.isEmpty())
                {
                    DialogConfig.getInstance().DisplayCustomToastMsg(FieldVisitCustomerDetailsPlanned.this, "Select PTP Time");
                }
                else if(ptpModeAmount.isEmpty())
                {
                    txtCashPtpAmount.requestFocus();
                    txtCashPtpAmount.setError("Enter PTP Amount");
                }
                else if(!customerMobile.isEmpty() && customerMobile.length() >= 0 && customerMobile.length() <= 10)
                {
                    if(!customerMobile.matches(Config.MOBILE_VALIDATOR))
                    {
                        txtCustomerMobile.requestFocus();
                        txtCustomerMobile.setError("Enter Valid Mobile Number");
                    }
                    else
                    {
                        ExecutePTPOperation();
                    }
                }
                else if(customerMobile.isEmpty() || customerMobile.length() <= 0)
                {
                    customerMobile = "";
                    ExecutePTPOperation();
                }
                else
                {
                    ExecutePTPOperation();
                }
            }

            if(rbPtpFile.isChecked())
            {
                //Set PTP Mode = FILE MODE
                ptpMode = Config.PTP_FILE;


                // Get Values from the TextBox
                ptpModeDate           = txtFilePtpDate.getText().toString().trim();
                fileApprovalAmount    = txtFileApprovalAmount.getText().toString().trim();
                downPaymentAmount     = txtFileDpAmount.getText().toString().trim();
                fileLoanAmount        = txtFileLoanAmount.getText().toString().trim();
                fileInstallmentNo     = txtFileNoOfInstallment.getText().toString().trim();
                fileInstallmentAmount = txtFileInstallmentAmount.getText().toString().trim();
                customerMobile        = txtCustomerMobile.getText().toString().trim();

                calcLoanAmount = txtLoanAmount.getText().toString().trim();
                calcEmi        = txtEmi.getText().toString().trim();
                calcRoi        = txtRoi.getText().toString().trim();
                calcTenure     = txtTenure.getText().toString().trim();


                // Check for Validation
                if(ptpModeDate.isEmpty())
                {
                    DialogConfig.getInstance().DisplayCustomToastMsg(FieldVisitCustomerDetailsPlanned.this, "Select File PTP Date");
                }
                else if(fileApprovalAmount.isEmpty())
                {
                    txtFileApprovalAmount.requestFocus();
                    txtFileApprovalAmount.setError("Enter File Approval Amount");
                }
                else if(downPaymentAmount.isEmpty())
                {
                    txtFileDpAmount.requestFocus();
                    txtFileDpAmount.setError("Enter Down Payment Amount");
                }
                else if(fileLoanAmount.isEmpty())
                {
                    txtFileLoanAmount.requestFocus();
                    txtFileLoanAmount.setError("Enter File Loan Amount");
                }
                else if(fileInstallmentNo.isEmpty())
                {
                    txtFileNoOfInstallment.requestFocus();
                    txtFileNoOfInstallment.setError("Enter No. of Installment");
                }
                else if(fileInstallmentAmount.isEmpty())
                {
                    txtFileInstallmentAmount.requestFocus();
                    txtFileInstallmentAmount.setError("Enter Installment Amount");
                }
                else if(!customerMobile.isEmpty() && customerMobile.length() >= 0 && customerMobile.length() <= 10)
                {
                    if(!customerMobile.matches(Config.MOBILE_VALIDATOR))
                    {
                        txtCustomerMobile.requestFocus();
                        txtCustomerMobile.setError("Enter Valid Mobile Number");
                    }
                    else
                    {
                        ExecutePTPOperation();
                    }
                }
                else if(customerMobile.isEmpty() || customerMobile.length() <= 0)
                {
                    customerMobile = "";
                    ExecutePTPOperation();
                }
                else
                {
                    ExecutePTPOperation();
                }
            }

            if(rbPtpSeize.isChecked())
            {
                //Set PTP Mode = SEIZE MODE
                ptpMode = Config.PTP_SEIZE;


                // Get Values from the TextBox
                ptpModeDate              = txtSeizePtpDate.getText().toString().trim();
                seizeVehicleName         = txtVehicleName.getText().toString().trim().toUpperCase();
                seizeVehicleNo           = txtVehicleNo.getText().toString().trim().toUpperCase();
                seizeEngineNo            = txtEngineNo.getText().toString().trim().toUpperCase();
                seizeChasisNo            = txtChasisNo.getText().toString().trim().toUpperCase();
                seizePoliceStationName   = txtPoliceStationName.getText().toString().trim().toUpperCase();
                seizePoliceStationCenter = txtPolicStationCenter.getText().toString().trim().toUpperCase();
                seizingAgencyName        = txtSeizeAgencyName.getText().toString().trim().toUpperCase();
                seizePoliceStationFax    = txtPoliceStationFAX.getText().toString().trim().toUpperCase();
                seizePoliceStationEmail  = txtPoliceStationEmail.getText().toString().trim();
                customerMobile           = txtCustomerMobile.getText().toString().trim();


                // Check for Validation
                if(ptpModeDate.isEmpty())
                {
                    DialogConfig.getInstance().DisplayCustomToastMsg(FieldVisitCustomerDetailsPlanned.this, "Select File PTP Date");
                }
                else if(seizeVehicleName.isEmpty())
                {
                    txtVehicleName.requestFocus();
                    txtVehicleName.setError("Enter Vehicle Name");
                }
                else if(seizeVehicleNo.isEmpty())
                {
                    txtVehicleNo.requestFocus();
                    txtVehicleNo.setError("Enter Vehicle Number");
                }
                else if(!seizeVehicleNo.matches(Config.VEHICLE_NO_FORMAT_1) && !seizeVehicleNo.matches(Config.VEHICLE_NO_FORMAT_2) && !seizeVehicleNo.matches(Config.VEHICLE_NO_FORMAT_3) && !seizeVehicleNo.matches(Config.VEHICLE_NO_FORMAT_4))
                {
                    txtVehicleNo.requestFocus();
                    txtVehicleNo.setError(" Enter Valid Vehicle Number");
                }
                else if(seizeEngineNo.isEmpty())
                {
                    txtEngineNo.requestFocus();
                    txtEngineNo.setError("Enter Engine Number");
                }
                else if(seizeChasisNo.isEmpty())
                {
                    txtChasisNo.requestFocus();
                    txtChasisNo.setError("Enter Chasis Number");
                }
                else if(seizePoliceStationName.isEmpty())
                {
                    txtPoliceStationName.requestFocus();
                    txtPoliceStationName.setError("Enter Police Station Name");
                }
                else if(seizePoliceStationCenter.isEmpty())
                {
                    txtPolicStationCenter.requestFocus();
                    txtPolicStationCenter.setError("Enter Police Station Center");
                }
                else if(seizingAgencyName.isEmpty())
                {
                    txtSeizeAgencyName.requestFocus();
                    txtSeizeAgencyName.setError("Enter Seizing Agency Name");
                }
                else if(seizePoliceStationFax.isEmpty())
                {
                    txtPoliceStationFAX.requestFocus();
                    txtPoliceStationFAX.setError("Enter Police Station Fax No");
                }
                else if(seizePoliceStationEmail.isEmpty())
                {
                    txtPoliceStationEmail.requestFocus();
                    txtPoliceStationEmail.setError("Enter Police Station Email");
                }
                else if(!seizePoliceStationEmail.matches(Config.EMAIL_PATTERN))
                {
                    txtPoliceStationEmail.requestFocus();
                    txtPoliceStationEmail.setError("Enter Valid Email");
                }
                else if(!customerMobile.isEmpty() && customerMobile.length() >= 0 && customerMobile.length() <= 10)
                {
                    if(!customerMobile.matches(Config.MOBILE_VALIDATOR))
                    {
                        txtCustomerMobile.requestFocus();
                        txtCustomerMobile.setError("Enter Valid Mobile Number");
                    }
                    else
                    {
                        ExecutePTPOperation();
                    }
                }
                else if(customerMobile.isEmpty() || customerMobile.length() <= 0)
                {
                    customerMobile = "";
                    ExecutePTPOperation();
                }
                else
                {
                    ExecutePTPOperation();
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/*** METHOD TO EXECUTE CALCULATE OPERATION ***//*


    public void ExecuteCalculateOperation(int userChoice)
    {
        try
        {
            switch (userChoice)
            {
                // If userChoice == LoanAmount
                case 1:

                    fileEmi     = Double.parseDouble(calcEmi);
                    fileRoi     = Double.parseDouble(calcRoi);
                    fileTenor   = Double.parseDouble(calcTenure);

                    fileLoanAmt = CalculateLoanAmount(fileEmi, fileRoi, fileTenor);

                    if(Double.isNaN(fileLoanAmt))
                    {
                        txtLoanAmount.setText("0");
                    }
                    else
                    {
                        txtLoanAmount.setText(String.valueOf(Math.ceil(fileLoanAmt)));
                    }

                    break;


                // If userChoice == EMI
                case 2:

                    fileLoanAmt = Double.parseDouble(calcLoanAmount);
                    fileRoi     = Double.parseDouble(calcRoi);
                    fileTenor   = Double.parseDouble(calcTenure);

                    fileEmi     = CalculateEmi(fileLoanAmt, fileRoi, fileTenor);

                    if(Double.isNaN(fileEmi))
                    {
                        txtEmi.setText("0");
                    }
                    else
                    {
                        txtEmi.setText(String.valueOf(Math.ceil(fileEmi)));
                    }

                    break;


                // If userChoice == R.O.I
                case 3:

                    fileLoanAmt = Double.parseDouble(calcLoanAmount);
                    fileEmi     = Double.parseDouble(calcEmi);
                    fileTenor   = Double.parseDouble(calcTenure);

                    fileRoi     = CalculateRateOfIneterest(fileEmi, fileLoanAmt, fileTenor);

                    if(Double.isNaN(fileRoi))
                    {
                        txtRoi.setText("0");
                    }
                    else
                    {
                        txtRoi.setText(String.format("%.2f", fileRoi));
                    }

                    break;


                // If userChoice == Tenure
                case 4:

                    fileLoanAmt = Double.parseDouble(calcLoanAmount);
                    fileEmi     = Double.parseDouble(calcEmi);
                    fileRoi     = Double.parseDouble(calcRoi);

                    fileTenor   = CalculateTenure(fileEmi, fileLoanAmt, fileRoi);

                    if(Double.isNaN(fileTenor))
                    {
                        txtTenure.setText("0");
                    }
                    else
                    {
                        txtTenure.setText(String.format("%.0f", fileTenor));
                    }

                    break;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/*** METHOD TO EXECUTE PTP OPERATION ***//*


    public void ExecutePTPOperation()
    {
        try
        {
            // Converting Date to SOAP Format
            ptpModeDate  = ptpModeDate.replace("/","-");
            datePTPMode  = originalFormat.parse(ptpModeDate);
            ptpModeDate  = targetFormat.format(datePTPMode);

            cDate        = new Date();

            // Compare Selected PTP Date with Current Date
            if (datePTPMode.after(cDate))
            {
                // Get Current DateTime
                entryDate = CommonFunctions.GetCurrentDateTime(Config.SOAP_DATE_FORMAT);

                // Set Default Values based on the Selection of Mode
                if(rbPtpCash.isChecked())
                {
                    // Converting Time to PT_H_M_S Format
                    ptpModeTime  = ConvertTimetoPTHMSFormat(ptpModeTime);

                    fileApprovalAmount = "";
                    downPaymentAmount = "";
                    fileLoanAmount = "";
                    fileInstallmentNo = "";
                    fileInstallmentAmount = "";
                    calcLoanAmount = "";
                    calcEmi = "";
                    calcRoi = "";
                    calcTenure = "";

                    seizeVehicleName = "";
                    seizeVehicleNo = "";
                    seizeEngineNo = "";
                    seizeChasisNo = "";
                    seizePoliceStationName = "";
                    seizePoliceStationCenter = "";
                    seizingAgencyName = "";
                    seizePoliceStationFax = "";
                    seizePoliceStationEmail = "";
                }

                if(rbPtpFile.isChecked())
                {
                    ptpModeTime = "";
                    ptpModeAmount = "";

                    seizeVehicleName = "";
                    seizeVehicleNo = "";
                    seizeEngineNo = "";
                    seizeChasisNo = "";
                    seizePoliceStationName = "";
                    seizePoliceStationCenter = "";
                    seizingAgencyName = "";
                    seizePoliceStationFax = "";
                    seizePoliceStationEmail = "";
                }

                if(rbPtpSeize.isChecked())
                {
                    ptpModeTime = "";
                    ptpModeAmount = "";

                    fileApprovalAmount = "";
                    downPaymentAmount = "";
                    fileLoanAmount = "";
                    fileInstallmentNo = "";
                    fileInstallmentAmount = "";
                    calcLoanAmount = "";
                    calcEmi = "";
                    calcRoi = "";
                    calcTenure = "";
                }


                // Call Method to Get PTP Count of Customer In Month
                GetPTPCountDataOfCustomerByCode();
            }
            else
            {
                DialogConfig.getInstance().DisplayCustomToastMsg(FieldVisitCustomerDetailsPlanned.this, "PTP Date must be future date");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/*** FUNCTION TO CONVERT TIME INTO AM / PM ***//*


    public String DisplayDateTime(int hour, int minute)
    {
        int selectedHour = hour;

        String msg = null;

        if (hour > 12)
        {
            selectedHour = selectedHour - 12;

            if (minute < 10)
            {
                msg = selectedHour + ":" + "0" + minute + " " + Config.PM;
            }
            else
            {
                msg = selectedHour + ":" + minute + " " + Config.PM;
            }
        }

        if (hour < 12)
        {
            if(hour == 0)
            {
                if (minute < 10)
                {
                    msg = (selectedHour + 12) + ":" + "0" + minute + " " + Config.AM;
                }
                else
                {
                    msg = (selectedHour + 12) + ":" + minute + " " + Config.AM;
                }
            }
            else
            {
                if (minute < 10)
                {
                    msg = selectedHour + ":" + "0" + minute + " " + Config.AM;
                }
                else
                {
                    msg = selectedHour + ":" + minute + " " + Config.AM;
                }
            }
        }

        if (hour == 12)
        {
            if (minute < 10)
            {
                msg = selectedHour + ":" + "0" + minute + " " + Config.PM;
            }
            else
            {
                msg = selectedHour + ":" + minute + " " + Config.PM;
            }
        }

        return msg;
    }


    */
/*** FUNCTION TO CONVERT TIME INTO AM / PM ***//*


    public String ConvertTimetoPTHMSFormat(String time)
    {
        String soapTime = null;

        if(time.contains(Config.PM))
        {
            String hour = time.split(":")[0];
            String minute = time.split(":")[1].split(" ")[0];

            int hr  = Integer.parseInt(hour);
            int min = Integer.parseInt(minute);
            int thr = hr;

            if(thr < 12)
            {
                hr = hr + 12;
            }

            soapTime = "PT" + hr + "H" + min + "M";
        }

        if(time.contains(Config.AM))
        {
            //String[] splitTime = time.split(":");
            String hour = time.split(":")[0];
            String minute = time.split(":")[1].split(" ")[0];

            int hr  = Integer.parseInt(hour);
            int min = Integer.parseInt(minute);

            if(hr == 12)
            {
                hr = 0;
            }

            soapTime = "PT" + hr + "H" + min + "M";
        }

        return soapTime;
    }


    */
/****** METHOD TO GET THE MAX PTP COUNT ALLOWED IN MONTH FOR SINGLE CUSTOMER BY CUSTOMER CODE, START DATE AND END DATE ******//*


    public void GetPTPCountDataOfCustomerByCode()
    {
        try
        {
            DialogConfig.getInstance().StartProgressDialog(FieldVisitCustomerDetailsPlanned.this, Config.PLEASE_WAIT);

            final Thread myThread = new Thread()
            {
                @Override
                public void run()
                {
                    super.run();

                    try
                    {
                        // Call Method to Get & Check PTP Count of Customer
                        CheckForPTPAllowedInMonth();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally
                    {
                        DialogConfig.getInstance().StopProgressDialog();
                    }
                }
            };

            myThread.start();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/********** METHOD TO GET PTP COUNT AND CHECK IT WITH MAX PTP ALLOWED PER MONTH **********//*


    public void CheckForPTPAllowedInMonth()
    {
        try
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    try
                    {
                        // Get End Date of PTP
                        endDate  = trgForm.format(datePTPMode);

                        // Get Start Date of PTP (One Month Before the Date of PTP)
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(datePTPMode);
                        cal.add(Calendar.MONTH, -1);
                        datePTPMode.setTime(cal.getTimeInMillis());
                        startDate = trgForm.format(datePTPMode);


                        // Initialize the object of Service Class
                        extraServiceObj = new UpdateDataService(EXTRA_SERVICE);

                        extraServiceObj.CallServiceWithoutObject(EXTRA_ACTION_1, GET_PTP_COUNT_OF_CUSTOMER,
                                "tem:customercode", customerCode,
                                "tem:StartDate", startDate,
                                "tem:EndDate", endDate);

                        ptpCountCustomer = (String) extraServiceObj.getVariableValue("GetAppCustomerPTPCountResult", String.class);

                        if (!ptpCountCustomer.isEmpty() && !ptpCountCustomer.equals("null") && ptpCountCustomer != null)
                        {
                            custPTPCount = Integer.parseInt(ptpCountCustomer);
                        }
                        else
                        {
                            custPTPCount = 0;
                            ptpCountCustomer = "0";
                        }


                        if(custPTPCount > maxPTP)
                        {
                            if(dialogPtpMode != null)
                            {
                                dialogPtpMode.dismiss();
                                dialogPtpMode = null;
                            }

                            // Call Method to Show Approval Dialog
                            DisplayPTPExtensionApprovalDialog();
                        }
                        else
                        {
                            // If USER ACCESS IS ONLINE
                            if(userAccess == 1)
                            {
                                if(checkNetwork.isInternetAvailable(getApplicationContext()))
                                {
                                    DialogConfig.getInstance().StartProgressDialog(FieldVisitCustomerDetailsPlanned.this, Config.PLEASE_WAIT);

                                    final Thread myThread = new Thread()
                                    {
                                        @Override
                                        public void run()
                                        {
                                            super.run();

                                            try
                                            {
                                                // Call Method to Save PTP Mode Visit Collection Data on Server
                                                SaveAndUpdateVisitCollectionDataONLINE(Integer.parseInt(Config.PTP_VISIT));
                                            }
                                            catch (Exception e)
                                            {
                                                e.printStackTrace();
                                            }
                                        }
                                    };

                                    myThread.start();
                                }
                                else
                                {
                                    DialogConfig.getInstance().ShowInternetDialog(FieldVisitCustomerDetailsPlanned.this);
                                }
                            }

                            // If USER ACCESS IS OFFLINE
                            if(userAccess == 0)
                            {
                                // Call Method to Save PTP Mode Visit Collection Data on Server
                                SaveAndUpdateVisitCollectionDataOFFLINE(Integer.parseInt(Config.PTP_VISIT));
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/*** METHOD TO SHOW MAIN APPROVAL DIALOG FOR COLLECTION LIMIT EXTENSION ***//*


public void DisplayPTPExtensionApprovalDialog()
{
    try
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                customAlertDialog = new Dialog(FieldVisitCustomerDetailsPlanned.this);
                customAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                customAlertDialog.setContentView(R.layout.dialog_common_approval_alert);
                customAlertDialog.setCancelable(true);

                tv_alert_msg   = (TextView) customAlertDialog.findViewById(R.id.tv_alert_msg);
                tv_alert_msg.setText(R.string.str_ptp_approval_msg);

                btnTakeApprove = (Button) customAlertDialog.findViewById(R.id.btnSendApproval);

                btnTakeApprove.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        new AlertDialog.Builder(FieldVisitCustomerDetailsPlanned.this)
                                .setIcon(R.mipmap.dialogimg)
                                .setTitle(Config.CONFIRMATION_TITLE)
                                .setMessage(Config.APPROVAL_CONFIRMATION)
                                .setPositiveButton(Config.YES, new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        ShowSendApprovalDialog();
                                    }

                                })
                                .setNegativeButton(Config.NO, null)
                                .show();
                    }
                });

                customAlertDialog.show();
                Window window = customAlertDialog.getWindow();
                assert window != null;
                window.setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
            }
        });
    }
    catch (Exception e)
    {
        e.printStackTrace();
    }
}


    */
/*** METHOD TO SHOW APPROVAL DIALOG TO SEND DAY BEGIN APPROVAL ***//*


    public void ShowSendApprovalDialog()
    {
        try
        {
            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    dialogApproval = new Dialog(FieldVisitCustomerDetailsPlanned.this);
                    dialogApproval.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialogApproval.setContentView(R.layout.dialog_common_approval_main);
                    dialogApproval.setCancelable(true);
                    dialogApproval.setCanceledOnTouchOutside(true);

                    if(customAlertDialog != null)
                    {
                        customAlertDialog.dismiss();
                        customAlertDialog = null;
                    }

                    tv_main_title   = (TextView) dialogApproval.findViewById(R.id.tv_main_title);
                    tv_main_title.setText(R.string.str_ptp_extension_approval_title);

                    btnSendApprove = (Button) dialogApproval.findViewById(R.id.btnSendApproval);
                    txtOtherReason = (EditText) dialogApproval.findViewById(R.id.txtReason);

                    btnSendApprove.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            try
                            {
                                approvalReason = "PTP EXTENSION";
                                approvalDescription = txtOtherReason.getText().toString().trim().toUpperCase();

                                if(approvalDescription.isEmpty() || approvalDescription.equals("null") || approvalDescription == null)
                                {
                                    txtOtherReason.requestFocus();
                                    txtOtherReason.setError("Please Specify Description");
                                }
                                else
                                {
                                    // Display Confirmation Dialog
                                    DisplayConfirmationDialog();
                                }
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    });

                    dialogApproval.show();
                    Window window = dialogApproval.getWindow();
                    assert window != null;
                    window.setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/******** METHOD TO SHOW CONFIRMATION DIALOG *********//*


    public void DisplayConfirmationDialog()
    {
        new AlertDialog.Builder(FieldVisitCustomerDetailsPlanned.this)
                .setIcon(R.mipmap.dialogimg)
                .setTitle(Config.CONFIRMATION_TITLE)
                .setMessage(Config.APPROVAL_CONFIRMATION)
                .setPositiveButton(Config.YES, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(checkNetwork.isInternetAvailable(getApplicationContext()))
                        {
                            DialogConfig.getInstance().StartProgressDialog(FieldVisitCustomerDetailsPlanned.this, Config.PLEASE_WAIT);

                            final Thread myThread = new Thread()
                            {
                                @Override
                                public void run()
                                {
                                    super.run();

                                    try
                                    {
                                        // Call Method to Send Approval Data on Server
                                        SendDayBeginApprovalData();
                                    }
                                    catch (Exception e)
                                    {
                                        e.printStackTrace();
                                    }
                                }
                            };

                            myThread.start();
                        }
                        else
                        {
                            DialogConfig.getInstance().ShowInternetDialog(FieldVisitCustomerDetailsPlanned.this);
                        }
                    }

                })
                .setNegativeButton(Config.NO, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if(dialogApproval != null)
                        {
                            dialogApproval.dismiss();
                            dialogApproval = null;
                        }
                    }
                })
                .show();
    }


    */
/******** METHOD TO SEND APPROVAL DATA TO SERVER *********//*


    public void SendDayBeginApprovalData()
    {
        try
        {
            // Set Approval Subject (COLLECTION_LIMIT_EXTENSION_APPROVAL)
            approvalFor = Config.PTP_EXTENSION;

            // Set Approval Date
            approvalDate = CommonFunctions.GetCurrentDateTime(Config.YYYY_MM_DD);
            approvalEntryDate = CommonFunctions.GetCurrentDateTime(Config.SOAP_DATE_FORMAT);

            // Initialize the obejct of Service Class
            approvalMasterServiceObj = new UpdateDataService(APPROVAL_MASTER_SERVICE);

            // Call Method to Check the Count of Data
            approvalMasterServiceObj.CallServiceObject(APPROVAL_MASTER_ACTION_1, APPROVAL_MASTER_COUNT, APPROVAL_MASTER_OBJ,
                    "bus:ApprovalDatetime", approvalDate,
                    "bus:ApprovalFor", approvalFor,
                    "bus:IsApprove", isApprove,
                    "bus:Isactive", isActive,
                    "bus:Isdelete", isDelete,
                    "bus:UserId", userId);

            // Get Count from Service Response
            int aprCnt = Integer.parseInt((String) approvalMasterServiceObj.getVariableValue("ApprovalMasterCollectionFromSearchFieldsCountResult", String.class));

            if (aprCnt == 0)
            {
                // Call Service to Insert the New Record
                approvalMasterServiceObj.CallServiceObject(APPROVAL_MASTER_ACTION_2, APPROVAL_MASTER_SAVE_DATA, APPROVAL_MASTER_OBJ,
                        "bus:ApprovalComment", approvalComment,
                        "bus:ApprovalDatetime", approvalDate,
                        "bus:ApprovalDescription", approvalDescription,
                        "bus:ApprovalFor", approvalFor,
                        "bus:ApprovalReason", approvalReason,
                        "bus:Bid", bId,
                        "bus:Cid", cId,
                        "bus:Entrydate", approvalEntryDate,
                        "bus:Extra1", extra1,
                        "bus:Extra2", extra2,
                        "bus:IsApprove", isApprove,
                        "bus:Isactive", isActive,
                        "bus:Isdelete", isDelete,
                        "bus:Issync", isSync,
                        "bus:Notes", notes,
                        "bus:Sid", sId,
                        "bus:Status", approvalStatus,
                        "bus:UserId", userId);
            }
            else
            {
                // Call Method to Get the Record ID to Update that Record
                approvalMasterServiceObj.CallServiceObject(APPROVAL_MASTER_ACTION_3, APPROVAL_MASTER_SEARCH_DATA, APPROVAL_MASTER_OBJ,
                        "bus:ApprovalDatetime", approvalDate,
                        "bus:ApprovalFor", approvalFor,
                        "bus:IsApprove", isApprove,
                        "bus:Isactive", isActive,
                        "bus:Isdelete", isDelete,
                        "bus:UserId", userId);

                String approvalId = (String) approvalMasterServiceObj.getVariableValue("a:Id", String.class);

                // Call Service to Update the Existing Record
                approvalMasterServiceObj.CallServiceObject(APPROVAL_MASTER_ACTION_4, APPROVAL_MASTER_UPDATE_DATA, APPROVAL_MASTER_OBJ,
                        "bus:ApprovalComment", approvalComment,
                        "bus:ApprovalDatetime", approvalDate,
                        "bus:ApprovalDescription", approvalDescription,
                        "bus:ApprovalFor", approvalFor,
                        "bus:ApprovalReason", approvalReason,
                        "bus:Bid", bId,
                        "bus:Cid", cId,
                        "bus:Entrydate", approvalEntryDate,
                        "bus:Id", approvalId,
                        "bus:Extra1", extra1,
                        "bus:Extra2", extra2,
                        "bus:IsApprove", isApprove,
                        "bus:Isactive", isActive,
                        "bus:Isdelete", isDelete,
                        "bus:Issync", isSync,
                        "bus:Notes", notes,
                        "bus:Sid", sId,
                        "bus:Status", approvalStatus,
                        "bus:UserId", userId);
            }

            runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    DialogConfig.getInstance().StopProgressDialog();

                    DialogConfig.getInstance().DisplayCustomToastMsg(FieldVisitCustomerDetailsPlanned.this, Config.APPROVAL_SUBMIT_MSG);

                    if(dialogApproval != null)
                    {
                        dialogApproval.dismiss();
                        dialogApproval = null;
                    }

                    finish();
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/*** METHOD TO SHOW FILE DIALOG ***//*


    public void DisplayFileDialog()
    {
        dialogFileMode = new Dialog(FieldVisitCustomerDetailsPlanned.this);
        dialogFileMode.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogFileMode.setContentView(R.layout.dialog_new_file_mode);
        dialogFileMode.setCancelable(true);

        lvFileDate               = (LinearLayout) dialogFileMode.findViewById(R.id.lvFileDate);
        lvFileApproveAmt         = (LinearLayout) dialogFileMode.findViewById(R.id.lvFileApproveAmount);
        lvFileDpAmt              = (LinearLayout) dialogFileMode.findViewById(R.id.lvFileDpAmount);
        lvFileLoanAmt            = (LinearLayout) dialogFileMode.findViewById(R.id.lvFileLoanAmount);
        lvFileInstallmentNo      = (LinearLayout) dialogFileMode.findViewById(R.id.lvFileInstallmentNo);
        lvFileInstallmentAmt     = (LinearLayout) dialogFileMode.findViewById(R.id.lvFileInstallmentAmount);
        lvLoanCalcTitle          = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoanCalcTitle);
        lvLoanCalcSelection      = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoanCalcSelection);
        lvLoanAmount             = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoanCalcLoanAmount);
        lvLoanEmi                = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoanCalcEmi);
        lvLoanRoi                = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoanCalcRoi);
        lvLoanTenure             = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoanCalcTenure);
        lvLoanCalculate          = (LinearLayout) dialogFileMode.findViewById(R.id.lvFileBtnCalculate);

        lvLoaneePhoto            = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoaneePhoto);
        lvLoaneeResdProof        = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoaneeResidentProof);
        lvLoaneeResdProofDetails = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoaneeResidentProofDetail);
        lvLoaneeIDProof          = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoaneeIdProof);
        lvLoaneeIDProofDetails   = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoaneeIdProofDetail);
        lvLoaneeSecurityCheque   = (LinearLayout) dialogFileMode.findViewById(R.id.lvLoaneeSecurityCheque);

        lvCoApp1Photo            = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantOnePhoto);
        lvCoApp1ResdProof        = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantOneResidentProof);
        lvCoApp1ResdProofDetails = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantOneResidentProofDetail);
        lvCoApp1IDProof          = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantOneIdProof);
        lvCoApp1IDProofDetails   = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantOneIdProofDetail);
        lvCoApp1SecurityCheque   = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantOneSecurityCheque);

        lvCoApp2Photo            = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantTwoPhoto);
        lvCoApp2ResdProof        = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantTwoResidentProof);
        lvCoApp2ResdProofDetails = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantTwoResidentProofDetail);
        lvCoApp2IDProof          = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantTwoIdProof);
        lvCoApp2IDProofDetails   = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantTwoIdProofDetail);
        lvCoApp2SecurityCheque   = (LinearLayout) dialogFileMode.findViewById(R.id.lvCoApplicantTwoSecurityCheque);

        txtLoaneeResdProofOthers = (EditText) dialogFileMode.findViewById(R.id.txtLoaneeResiProofOther);
        txtLoaneeIDProofOthers   = (EditText) dialogFileMode.findViewById(R.id.txtLoaneeIdProofOther);
        txtCoApp1ResdProofOthers = (EditText) dialogFileMode.findViewById(R.id.txtCoAppOneResiProofOther);
        txtCoApp1IDProofOthers   = (EditText) dialogFileMode.findViewById(R.id.txtCoAppOneIdProofOther);
        txtCoApp2ResdProofOthers = (EditText) dialogFileMode.findViewById(R.id.txtCoAppTwoResiProofOther);
        txtCoApp2IDProofOthers   = (EditText) dialogFileMode.findViewById(R.id.txtCoAppTwoIdProofOther);

        rbGrpLoanee               = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpLoanee);
        rbGrpLoaneePhoto          = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpLoaneePhoto);
        rbGrpLoaneeResdProof      = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpLoaneeResdProof);
        rbGrpLoaneeIDProof        = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpLoaneeIdProof);
        rbGrpLoaneeSecurityCheque = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpLoaneeSecurityCheque);

        rbGrpCoApp1               = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpCoAppOne);
        rbGrpCoApp1Photo          = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpCoAppOnePhoto);
        rbGrpCoApp1ResdProof      = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpCoAppOneResdProof);
        rbGrpCoApp1IDProof        = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpCoAppOneIdProof);
        rbGrpCoApp1SecurityCheque = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpCoAppOneSecurityCheque);

        rbGrpCoApp2               = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpCoAppTwo);
        rbGrpCoApp2Photo          = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpCoAppTwoPhoto);
        rbGrpCoApp2ResdProof      = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpCoAppTwoResdProof);
        rbGrpCoApp2IDProof        = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpCoAppTwoIdProof);
        rbGrpCoApp2SecurityCheque = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpCoAppTwoSecurityCheque);

        btnFileLoanCalculate      = (Button) dialogFileMode.findViewById(R.id.btnLoanCalculator);
        btnFileSubmit             = (Button) dialogFileMode.findViewById(R.id.btnFileSubmit);

        chLoaneeResdLightBill     = (CheckBox) dialogFileMode.findViewById(R.id.chbLoaneeResiProofLightBill);
        chLoaneeResdTaxBill       = (CheckBox) dialogFileMode.findViewById(R.id.chbLoaneeResiProofTaxBill);
        chLoaneeResdAadharCard    = (CheckBox) dialogFileMode.findViewById(R.id.chbLoaneeResiProofAdharCard);
        chLoaneeResdOther         = (CheckBox) dialogFileMode.findViewById(R.id.chbLoaneeResiProofOther);
        chLoaneeIDPanCard         = (CheckBox) dialogFileMode.findViewById(R.id.chbLoaneeIdProofPanCard);
        chLoaneeIDElectionCard    = (CheckBox) dialogFileMode.findViewById(R.id.chbLoaneeIdProofElectionCard);
        chLoaneeIDAadharCard      = (CheckBox) dialogFileMode.findViewById(R.id.chbLoaneeIdProofAdharCard);
        chLoaneeIDDrivingLicence  = (CheckBox) dialogFileMode.findViewById(R.id.chbLoaneeIdProofDrivingLicence);
        chLoaneeIDOther           = (CheckBox) dialogFileMode.findViewById(R.id.chbLoaneeIdProofOther);

        chCoApp1ResdLightBill     = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppOneResiProofLightBill);
        chCoApp1ResdTaxBill       = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppOneResiProofTaxBill);
        chCoApp1ResdAadharCard    = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppOneResiProofAdharCard);
        chCoApp1ResdOther         = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppOneResiProofOther);
        chCoApp1IDPanCard         = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppOneIdProofPanCard);
        chCoApp1IDElectionCard    = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppOneIdProofElectionCard);
        chCoApp1IDAadharCard      = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppOneIdProofAdharCard);
        chCoApp1IDDrivingLicence  = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppOneIdProofDrivingLicence);
        chCoApp1IDOther           = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppOneIdProofOther);

        chCoApp2ResdLightBill     = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppTwoResiProofLightBill);
        chCoApp2ResdTaxBill       = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppTwoResiProofTaxBill);
        chCoApp2ResdAadharCard    = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppTwoResiProofAdharCard);
        chCoApp2ResdOther         = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppTwoResiProofOther);
        chCoApp2IDPanCard         = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppTwoIdProofPanCard);
        chCoApp2IDElectionCard    = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppTwoIdProofElectionCard);
        chCoApp2IDAadharCard      = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppTwoIdProofAdharCard);
        chCoApp2IDDrivingLicence  = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppTwoIdProofDrivingLicence);
        chCoApp2IDOther           = (CheckBox) dialogFileMode.findViewById(R.id.chbCoAppTwoIdProofOther);

        rbLoaneeYes               = (RadioButton) dialogFileMode.findViewById(R.id.rbLoaneeYes);
        rbLoaneeNo                = (RadioButton) dialogFileMode.findViewById(R.id.rbLoaneeNo);
        rbLoaneePhotoYes          = (RadioButton) dialogFileMode.findViewById(R.id.rbLoaneePhotoYes);
        rbLoaneePhotoNo           = (RadioButton) dialogFileMode.findViewById(R.id.rbLoaneePhotoNo);
        rbLoaneeResdProofYes      = (RadioButton) dialogFileMode.findViewById(R.id.rbLoaneeResiProofYes);
        rbLoaneeResdProofNo       = (RadioButton) dialogFileMode.findViewById(R.id.rbLoaneeResiProofNo);
        rbLoaneeIdProofYes        = (RadioButton) dialogFileMode.findViewById(R.id.rbLoaneeIdProofYes);
        rbLoaneeIdProofNo         = (RadioButton) dialogFileMode.findViewById(R.id.rbLoaneeIdProofNo);
        rbLoaneeSecurityChequeYes = (RadioButton) dialogFileMode.findViewById(R.id.rbLoaneeSecurityChequeYes);
        rbLoaneeSecurityChequeNo  = (RadioButton) dialogFileMode.findViewById(R.id.rbLoaneeSecurityChequeNo);

        rbCoApp1Yes               = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppOneYes);
        rbCoApp1No                = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppOneNo);
        rbCoApp1PhotoYes          = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppOnePhotoYes);
        rbCoApp1PhotoNo           = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppOnePhotoNo);
        rbCoApp1ResdProofYes      = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppOneResiProofYes);
        rbCoApp1ResdProofNo       = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppOneResiProofNo);
        rbCoApp1IdProofYes        = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppOneIdProofYes);
        rbCoApp1IdProofNo         = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppOneIdProofNo);
        rbCoApp1SecurityChequeYes = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppOneSecurityChequeYes);
        rbCoApp1SecurityChequeNo  = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppOneSecurityChequeNo);

        rbCoApp2Yes               = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppTwoYes);
        rbCoApp2No                = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppTwoNo);
        rbCoApp2PhotoYes          = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppTwoPhotoYes);
        rbCoApp2PhotoNo           = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppTwoPhotoNo);
        rbCoApp2ResdProofYes      = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppTwoResiProofYes);
        rbCoApp2ResdProofNo       = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppTwoResiProofNo);
        rbCoApp2IdProofYes        = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppTwoIdProofYes);
        rbCoApp2IdProofNo         = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppTwoIdProofNo);
        rbCoApp2SecurityChequeYes = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppTwoSecurityChequeYes);
        rbCoApp2SecurityChequeNo  = (RadioButton) dialogFileMode.findViewById(R.id.rbCoAppTwoSecurityChequeNo);

        txtFileApprovalAmount     = (EditText) dialogFileMode.findViewById(R.id.txtFileApproveAmount);
        txtFileDpAmount           = (EditText) dialogFileMode.findViewById(R.id.txtDownPaymentAmount);
        txtFileLoanAmount         = (EditText) dialogFileMode.findViewById(R.id.txtFileLoanAmount);
        txtFileNoOfInstallment    = (EditText) dialogFileMode.findViewById(R.id.txtFileInstallmentNo);
        txtFileInstallmentAmount  = (EditText) dialogFileMode.findViewById(R.id.txtFileInstallmentAmount);
        txtCustomerMobile         = (EditText) dialogFileMode.findViewById(R.id.txtCustomerMobile);

        rbGrpLoanCalc    = (RadioGroup) dialogFileMode.findViewById(R.id.rbGrpLoanCalc);

        rbLoanAmt        = (RadioButton) dialogFileMode.findViewById(R.id.rbLoanAmt);
        rbEmi            = (RadioButton) dialogFileMode.findViewById(R.id.rbEmi);
        rbRoi            = (RadioButton) dialogFileMode.findViewById(R.id.rbRoi);
        rbTenure         = (RadioButton) dialogFileMode.findViewById(R.id.rbTenure);

        txtLoanAmount    = (EditText) dialogFileMode.findViewById(R.id.txtLoanAmount);
        txtEmi           = (EditText) dialogFileMode.findViewById(R.id.txtEmi);
        txtRoi           = (EditText) dialogFileMode.findViewById(R.id.txtRoi);
        txtTenure        = (EditText) dialogFileMode.findViewById(R.id.txtTenure);


        */
/**** FOR LOANEE ****//*


        int rbLoaneeSelected = rbGrpLoanee.getCheckedRadioButtonId();
        rbLoanee = (RadioButton) dialogFileMode.findViewById(rbLoaneeSelected);

        rbGrpLoanee.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        case R.id.rbLoaneeYes:

                            lvLoaneePhoto.setVisibility(View.VISIBLE);
                            lvLoaneeResdProof.setVisibility(View.VISIBLE);
                            lvLoaneeIDProof.setVisibility(View.VISIBLE);
                            lvLoaneeSecurityCheque.setVisibility(View.VISIBLE);

                            break;

                        case R.id.rbLoaneeNo:

                            lvLoaneePhoto.setVisibility(View.GONE);
                            lvLoaneeResdProof.setVisibility(View.GONE);
                            lvLoaneeResdProofDetails.setVisibility(View.GONE);
                            txtLoaneeResdProofOthers.setVisibility(View.GONE);
                            lvLoaneeIDProof.setVisibility(View.GONE);
                            lvLoaneeIDProofDetails.setVisibility(View.GONE);
                            txtLoaneeIDProofOthers.setVisibility(View.GONE);
                            lvLoaneeSecurityCheque.setVisibility(View.GONE);

                            rbLoaneePhotoNo.setChecked(true);
                            rbLoaneeResdProofNo.setChecked(true);
                            rbLoaneeIdProofNo.setChecked(true);
                            rbLoaneeSecurityChequeNo.setChecked(true);

                            chLoaneeResdLightBill.setChecked(false);
                            chLoaneeResdTaxBill.setChecked(false);
                            chLoaneeResdAadharCard.setChecked(false);
                            chLoaneeResdOther.setChecked(false);

                            chLoaneeIDPanCard.setChecked(false);
                            chLoaneeIDElectionCard.setChecked(false);
                            chLoaneeIDAadharCard.setChecked(false);
                            chLoaneeIDDrivingLicence.setChecked(false);
                            chLoaneeIDOther.setChecked(false);

                            txtLoaneeResdProofOthers.setText("");
                            txtLoaneeIDProofOthers.setText("");

                            break;
                    }
                }
            }
        });


        int rbLoaneeResdProofSelected = rbGrpLoaneeResdProof.getCheckedRadioButtonId();
        rbLoaneeResdProof = (RadioButton) dialogFileMode.findViewById(rbLoaneeResdProofSelected);

        rbGrpLoaneeResdProof.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        case R.id.rbLoaneeResiProofYes:

                            lvLoaneeResdProofDetails.setVisibility(View.VISIBLE);

                            break;


                        case R.id.rbLoaneeResiProofNo:

                            lvLoaneeResdProofDetails.setVisibility(View.GONE);
                            txtLoaneeResdProofOthers.setVisibility(View.GONE);

                            chLoaneeResdLightBill.setChecked(false);
                            chLoaneeResdTaxBill.setChecked(false);
                            chLoaneeResdAadharCard.setChecked(false);
                            chLoaneeResdOther.setChecked(false);

                            txtLoaneeResdProofOthers.setText("");

                            break;
                    }
                }
            }
        });


        int rbLoaneeIDProofSelected = rbGrpLoaneeIDProof.getCheckedRadioButtonId();
        rbLoaneeIDProof = (RadioButton) dialogFileMode.findViewById(rbLoaneeIDProofSelected);

        rbGrpLoaneeIDProof.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        case R.id.rbLoaneeIdProofYes:

                            lvLoaneeIDProofDetails.setVisibility(View.VISIBLE);

                            break;


                        case R.id.rbLoaneeIdProofNo:

                            lvLoaneeIDProofDetails.setVisibility(View.GONE);
                            txtLoaneeIDProofOthers.setVisibility(View.GONE);

                            chLoaneeIDPanCard.setChecked(false);
                            chLoaneeIDElectionCard.setChecked(false);
                            chLoaneeIDAadharCard.setChecked(false);
                            chLoaneeIDDrivingLicence.setChecked(false);
                            chLoaneeIDOther.setChecked(false);

                            txtLoaneeIDProofOthers.setText("");

                            break;
                    }
                }
            }
        });

        chLoaneeResdOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (chLoaneeResdOther.isChecked())
                {
                    txtLoaneeResdProofOthers.setVisibility(View.VISIBLE);
                }
                else
                {
                    txtLoaneeResdProofOthers.setVisibility(View.GONE);
                    txtLoaneeResdProofOthers.setText("");
                }
            }
        });

        chLoaneeIDOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (chLoaneeIDOther.isChecked())
                {
                    txtLoaneeIDProofOthers.setVisibility(View.VISIBLE);
                }
                else
                {
                    txtLoaneeIDProofOthers.setVisibility(View.GONE);
                    txtLoaneeIDProofOthers.setText("");
                }
            }
        });


        */
/**** FOR CO-APPLICANT 1 ****//*


        int rbCoApp1Selected = rbGrpCoApp1.getCheckedRadioButtonId();
        rbCoApp1 = (RadioButton) dialogFileMode.findViewById(rbCoApp1Selected);

        rbGrpCoApp1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        // IF PTP MODE = CASH
                        case R.id.rbCoAppOneYes:

                            lvCoApp1Photo.setVisibility(View.VISIBLE);
                            lvCoApp1ResdProof.setVisibility(View.VISIBLE);
                            lvCoApp1IDProof.setVisibility(View.VISIBLE);
                            lvCoApp1SecurityCheque.setVisibility(View.VISIBLE);

                            break;


                        // IF PTP MODE = FILE
                        case R.id.rbCoAppOneNo:

                            lvCoApp1Photo.setVisibility(View.GONE);
                            lvCoApp1ResdProof.setVisibility(View.GONE);
                            lvCoApp1ResdProofDetails.setVisibility(View.GONE);
                            txtCoApp1ResdProofOthers.setVisibility(View.GONE);
                            lvCoApp1IDProof.setVisibility(View.GONE);
                            lvCoApp1IDProofDetails.setVisibility(View.GONE);
                            txtCoApp1IDProofOthers.setVisibility(View.GONE);
                            lvCoApp1SecurityCheque.setVisibility(View.GONE);

                            rbCoApp1PhotoNo.setChecked(true);
                            rbCoApp1ResdProofNo.setChecked(true);
                            rbCoApp1IdProofNo.setChecked(true);
                            rbCoApp1SecurityChequeNo.setChecked(true);

                            chCoApp1ResdLightBill.setChecked(false);
                            chCoApp1ResdTaxBill.setChecked(false);
                            chCoApp1ResdAadharCard.setChecked(false);
                            chCoApp1ResdOther.setChecked(false);

                            chCoApp1IDPanCard.setChecked(false);
                            chCoApp1IDElectionCard.setChecked(false);
                            chCoApp1IDAadharCard.setChecked(false);
                            chCoApp1IDDrivingLicence.setChecked(false);
                            chCoApp1IDOther.setChecked(false);

                            txtCoApp1ResdProofOthers.setText("");
                            txtCoApp1IDProofOthers.setText("");

                            break;
                    }
                }
            }
        });


        int rbCoApp1ResdProofSelected = rbGrpCoApp1ResdProof.getCheckedRadioButtonId();
        rbCoApp1ResdProof = (RadioButton) dialogFileMode.findViewById(rbCoApp1ResdProofSelected);

        rbGrpCoApp1ResdProof.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        case R.id.rbCoAppOneResiProofYes:

                            lvCoApp1ResdProofDetails.setVisibility(View.VISIBLE);

                            break;


                        case R.id.rbCoAppOneResiProofNo:

                            lvCoApp1ResdProofDetails.setVisibility(View.GONE);
                            txtCoApp1ResdProofOthers.setVisibility(View.GONE);

                            chCoApp1ResdLightBill.setChecked(false);
                            chCoApp1ResdTaxBill.setChecked(false);
                            chCoApp1ResdAadharCard.setChecked(false);
                            chCoApp1ResdOther.setChecked(false);

                            txtCoApp1ResdProofOthers.setText("");

                            break;
                    }
                }
            }
        });


        int rbCoApp1IDProofSelected = rbGrpCoApp1IDProof.getCheckedRadioButtonId();
        rbCoApp1IDProof = (RadioButton) dialogFileMode.findViewById(rbCoApp1IDProofSelected);

        rbGrpCoApp1IDProof.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        case R.id.rbCoAppOneIdProofYes:

                            lvCoApp1IDProofDetails.setVisibility(View.VISIBLE);

                            break;


                        case R.id.rbCoAppOneIdProofNo:

                            lvCoApp1IDProofDetails.setVisibility(View.GONE);
                            txtCoApp1IDProofOthers.setVisibility(View.GONE);

                            chCoApp1IDPanCard.setChecked(false);
                            chCoApp1IDElectionCard.setChecked(false);
                            chCoApp1IDAadharCard.setChecked(false);
                            chCoApp1IDDrivingLicence.setChecked(false);
                            chCoApp1IDOther.setChecked(false);

                            txtCoApp1IDProofOthers.setText("");

                            break;
                    }
                }
            }
        });

        chCoApp1ResdOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (chCoApp1ResdOther.isChecked())
                {
                    txtCoApp1ResdProofOthers.setVisibility(View.VISIBLE);
                }
                else
                {
                    txtCoApp1ResdProofOthers.setVisibility(View.GONE);
                    txtCoApp1ResdProofOthers.setText("");
                }
            }
        });

        chCoApp1IDOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (chCoApp1IDOther.isChecked())
                {
                    txtCoApp1IDProofOthers.setVisibility(View.VISIBLE);
                }
                else
                {
                    txtCoApp1IDProofOthers.setVisibility(View.GONE);
                    txtCoApp1IDProofOthers.setText("");
                }
            }
        });


        */
/**** FOR CO-APPLICANT 2 ****//*


        int rbCoApp2Selected = rbGrpCoApp2.getCheckedRadioButtonId();
        rbCoApp2 = (RadioButton) dialogFileMode.findViewById(rbCoApp2Selected);

        rbGrpCoApp2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        // IF PTP MODE = CASH
                        case R.id.rbCoAppTwoYes:

                            lvCoApp2Photo.setVisibility(View.VISIBLE);
                            lvCoApp2ResdProof.setVisibility(View.VISIBLE);
                            lvCoApp2IDProof.setVisibility(View.VISIBLE);
                            lvCoApp2SecurityCheque.setVisibility(View.VISIBLE);

                            break;


                        // IF PTP MODE = FILE
                        case R.id.rbCoAppTwoNo:

                            lvCoApp2Photo.setVisibility(View.GONE);
                            lvCoApp2ResdProof.setVisibility(View.GONE);
                            lvCoApp2ResdProofDetails.setVisibility(View.GONE);
                            txtCoApp2ResdProofOthers.setVisibility(View.GONE);
                            lvCoApp2IDProof.setVisibility(View.GONE);
                            lvCoApp2IDProofDetails.setVisibility(View.GONE);
                            txtCoApp2IDProofOthers.setVisibility(View.GONE);
                            lvCoApp2SecurityCheque.setVisibility(View.GONE);

                            rbCoApp2PhotoNo.setChecked(true);
                            rbCoApp2ResdProofNo.setChecked(true);
                            rbCoApp2IdProofNo.setChecked(true);
                            rbCoApp2SecurityChequeNo.setChecked(true);

                            chCoApp2ResdLightBill.setChecked(false);
                            chCoApp2ResdTaxBill.setChecked(false);
                            chCoApp2ResdAadharCard.setChecked(false);
                            chCoApp2ResdOther.setChecked(false);

                            chCoApp2IDPanCard.setChecked(false);
                            chCoApp2IDElectionCard.setChecked(false);
                            chCoApp2IDAadharCard.setChecked(false);
                            chCoApp2IDDrivingLicence.setChecked(false);
                            chCoApp2IDOther.setChecked(false);

                            txtCoApp2ResdProofOthers.setText("");
                            txtCoApp2IDProofOthers.setText("");

                            break;
                    }
                }
            }
        });


        int rbCoApp2ResdProofSelected = rbGrpCoApp2ResdProof.getCheckedRadioButtonId();
        rbCoApp2ResdProof = (RadioButton) dialogFileMode.findViewById(rbCoApp2ResdProofSelected);

        rbGrpCoApp2ResdProof.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        case R.id.rbCoAppTwoResiProofYes:

                            lvCoApp2ResdProofDetails.setVisibility(View.VISIBLE);

                            break;


                        case R.id.rbCoAppTwoResiProofNo:

                            lvCoApp2ResdProofDetails.setVisibility(View.GONE);
                            txtCoApp2ResdProofOthers.setVisibility(View.GONE);

                            chCoApp2ResdLightBill.setChecked(false);
                            chCoApp2ResdTaxBill.setChecked(false);
                            chCoApp2ResdAadharCard.setChecked(false);
                            chCoApp2ResdOther.setChecked(false);

                            txtCoApp2ResdProofOthers.setText("");

                            break;
                    }
                }
            }
        });


        int rbCoApp2IDProofSelected = rbGrpCoApp2IDProof.getCheckedRadioButtonId();
        rbCoApp2IDProof = (RadioButton) dialogFileMode.findViewById(rbCoApp2IDProofSelected);

        rbGrpCoApp2IDProof.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        case R.id.rbCoAppTwoIdProofYes:

                            lvCoApp2IDProofDetails.setVisibility(View.VISIBLE);

                            break;


                        case R.id.rbCoAppTwoIdProofNo:

                            lvCoApp2IDProofDetails.setVisibility(View.GONE);
                            txtCoApp2IDProofOthers.setVisibility(View.GONE);

                            chCoApp2IDPanCard.setChecked(false);
                            chCoApp2IDElectionCard.setChecked(false);
                            chCoApp2IDAadharCard.setChecked(false);
                            chCoApp2IDDrivingLicence.setChecked(false);
                            chCoApp2IDOther.setChecked(false);

                            txtCoApp2IDProofOthers.setText("");

                            break;
                    }
                }
            }
        });

        chCoApp2ResdOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (chCoApp2ResdOther.isChecked())
                {
                    txtCoApp2ResdProofOthers.setVisibility(View.VISIBLE);
                }
                else
                {
                    txtCoApp2ResdProofOthers.setVisibility(View.GONE);
                    txtCoApp2ResdProofOthers.setText("");
                }
            }
        });

        chCoApp2IDOther.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                if (chCoApp2IDOther.isChecked())
                {
                    txtCoApp2IDProofOthers.setVisibility(View.VISIBLE);
                }
                else
                {
                    txtCoApp2IDProofOthers.setVisibility(View.GONE);
                    txtCoApp2ResdProofOthers.setText("");
                }
            }
        });


        */
/*** FOR LOAN CALCULATOR ***//*


        int rbLoanCalcSelected = rbGrpLoanCalc.getCheckedRadioButtonId();
        rbLoanCalcType = (RadioButton) dialogFileMode.findViewById(rbLoanCalcSelected);

        if(rbLoanCalcType == rbLoanAmt)
        {
            txtLoanAmount.setEnabled(false);
            txtEmi.setEnabled(true);
            txtRoi.setEnabled(true);
            txtTenure.setEnabled(true);

            txtLoanAmount.setBackgroundResource(R.drawable.disabledittext);
            txtEmi.setBackgroundResource(R.drawable.customedittext);
            txtRoi.setBackgroundResource(R.drawable.customedittext);
            txtTenure.setBackgroundResource(R.drawable.customedittext);

            txtEmi.requestFocus();
            emptyLoanCalcTextValue();
        }


        rbGrpLoanCalc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                if (checkedId > -1)
                {
                    switch (checkedId)
                    {
                        case R.id.rbLoanAmt:

                            txtLoanAmount.setEnabled(false);
                            txtEmi.setEnabled(true);
                            txtRoi.setEnabled(true);
                            txtTenure.setEnabled(true);

                            txtLoanAmount.setBackgroundResource(R.drawable.disabledittext);
                            txtEmi.setBackgroundResource(R.drawable.customedittext);
                            txtRoi.setBackgroundResource(R.drawable.customedittext);
                            txtTenure.setBackgroundResource(R.drawable.customedittext);

                            txtEmi.requestFocus();
                            emptyLoanCalcTextValue();

                            break;


                        case R.id.rbEmi:

                            txtLoanAmount.setEnabled(true);
                            txtEmi.setEnabled(false);
                            txtRoi.setEnabled(true);
                            txtTenure.setEnabled(true);

                            txtLoanAmount.setBackgroundResource(R.drawable.customedittext);
                            txtEmi.setBackgroundResource(R.drawable.disabledittext);
                            txtRoi.setBackgroundResource(R.drawable.customedittext);
                            txtTenure.setBackgroundResource(R.drawable.customedittext);

                            txtLoanAmount.requestFocus();
                            emptyLoanCalcTextValue();

                            break;


                        case R.id.rbRoi:

                            txtLoanAmount.setEnabled(true);
                            txtEmi.setEnabled(true);
                            txtRoi.setEnabled(false);
                            txtTenure.setEnabled(true);

                            txtLoanAmount.setBackgroundResource(R.drawable.customedittext);
                            txtEmi.setBackgroundResource(R.drawable.customedittext);
                            txtRoi.setBackgroundResource(R.drawable.disabledittext);
                            txtTenure.setBackgroundResource(R.drawable.customedittext);

                            txtLoanAmount.requestFocus();
                            emptyLoanCalcTextValue();

                            break;


                        case R.id.rbTenure:

                            txtLoanAmount.setEnabled(true);
                            txtEmi.setEnabled(true);
                            txtRoi.setEnabled(true);
                            txtTenure.setEnabled(false);

                            txtLoanAmount.setBackgroundResource(R.drawable.customedittext);
                            txtEmi.setBackgroundResource(R.drawable.customedittext);
                            txtRoi.setBackgroundResource(R.drawable.customedittext);
                            txtTenure.setBackgroundResource(R.drawable.disabledittext);

                            txtLoanAmount.requestFocus();
                            emptyLoanCalcTextValue();

                            break;
                    }
                }
            }
        });


        */
/*** METHOD TO PERFORM ONCLICK EVENT OF CALCULATE BUTTON IN FILE MODE OPERATION ***//*


        btnFileLoanCalculate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    calcLoanAmount = txtLoanAmount.getText().toString().trim();
                    calcEmi        = txtEmi.getText().toString().trim();
                    calcRoi        = txtRoi.getText().toString().trim();
                    calcTenure     = txtTenure.getText().toString().trim();


                    // If Loan Amount is Checked
                    if(rbLoanAmt.isChecked())
                    {
                        if(calcEmi.isEmpty())
                        {
                            txtEmi.requestFocus();
                            txtEmi.setError("Enter EMI Value");
                        }
                        else if(calcRoi.isEmpty())
                        {
                            txtRoi.requestFocus();
                            txtRoi.setError("Enter Rate of Interest");
                        }
                        else if(calcTenure.isEmpty())
                        {
                            txtTenure.requestFocus();
                            txtTenure.setError("Enter Tenure");
                        }
                        else
                        {
                            ExecuteCalculateOperation(1);
                        }
                    }

                    // If EMI is Checked
                    if(rbEmi.isChecked())
                    {
                        if(calcLoanAmount.isEmpty())
                        {
                            txtLoanAmount.requestFocus();
                            txtLoanAmount.setError("Enter Loan Amount");
                        }
                        else if(calcRoi.isEmpty())
                        {
                            txtRoi.requestFocus();
                            txtRoi.setError("Enter Rate of Interest");
                        }
                        else if(calcTenure.isEmpty())
                        {
                            txtTenure.requestFocus();
                            txtTenure.setError("Enter Tenure");
                        }
                        else
                        {
                            ExecuteCalculateOperation(2);
                        }
                    }

                    // If ROI is Checked
                    if(rbRoi.isChecked())
                    {
                        if(calcLoanAmount.isEmpty())
                        {
                            txtLoanAmount.requestFocus();
                            txtLoanAmount.setError("Enter Loan Amount");
                        }
                        else if(calcEmi.isEmpty())
                        {
                            txtEmi.requestFocus();
                            txtEmi.setError("Enter EMI Value");
                        }
                        else if(calcTenure.isEmpty())
                        {
                            txtTenure.requestFocus();
                            txtTenure.setError("Enter Tenure");
                        }
                        else
                        {
                            ExecuteCalculateOperation(3);
                        }
                    }

                    // If Tenure is Checked
                    if(rbTenure.isChecked())
                    {
                        if(calcLoanAmount.isEmpty())
                        {
                            txtLoanAmount.requestFocus();
                            txtLoanAmount.setError("Enter Loan Amount");
                        }
                        else if(calcEmi.isEmpty())
                        {
                            txtEmi.requestFocus();
                            txtEmi.setError("Enter EMI Value");
                        }
                        else if(calcRoi.isEmpty())
                        {
                            txtRoi.requestFocus();
                            txtRoi.setError("Enter Rate of Interest");
                        }
                        else
                        {
                            ExecuteCalculateOperation(4);
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


        txtFileApprovalAmount.requestFocus();


        */
/*** METHOD TO PERFORM ONCLICK EVENT OF SUBMIT BUTTON IN FILE MODE OPERATION ***//*


        btnFileSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    ExecuteFILEOperation();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


        // Call Method to Execute Loan Amount Calculation
        ExecuteLoanAmountCalculation();


        dialogFileMode.show();
        Window window = dialogFileMode.getWindow();
        assert window != null;
        window.setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
    }


    */
/*** METHOD TO EXECUTE LOAN AMOUNT CALCULATION OPERATION ***//*


    public void ExecuteLoanAmountCalculation()
    {
        try
        {
            txtFileApprovalAmount.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
                {
                    fileApprovalAmount = txtFileApprovalAmount.getText().toString().trim();

                    if(fileApprovalAmount.length() <= 0 || fileApprovalAmount.equals("") || fileApprovalAmount.isEmpty())
                    {
                        fileApprovalAmount = "0";
                        fApproveAmt = 0.0;
                        CalculateFileLoanAmount(fApproveAmt, fDpAmt);
                    }
                    else
                    {
                        fApproveAmt = Double.parseDouble(fileApprovalAmount);

                        if(downPaymentAmount.length() > 0 && !downPaymentAmount.equals("0"))
                        {
                            if(fApproveAmt < fDpAmt)
                            {
                                fDpAmt = 0.0;
                                downPaymentAmount = "0";
                                txtFileDpAmount.setText("");
                                txtFileApprovalAmount.requestFocus();

                                CalculateFileLoanAmount(fApproveAmt, fDpAmt);
                            }
                            else
                            {
                                CalculateFileLoanAmount(fApproveAmt, fDpAmt);
                            }
                        }
                        else
                        {
                            CalculateFileLoanAmount(fApproveAmt, fDpAmt);
                        }
                    }
                }
            });


            txtFileDpAmount.addTextChangedListener(new TextWatcher()
            {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
                {
                    downPaymentAmount = txtFileDpAmount.getText().toString().trim();

                    if(downPaymentAmount.length() <= 0 || downPaymentAmount.equals("") || downPaymentAmount.isEmpty())
                    {
                        downPaymentAmount = "0";
                        fDpAmt = 0.0;
                        CalculateFileLoanAmount(fApproveAmt, fDpAmt);
                    }
                    else
                    {
                        fDpAmt = Double.parseDouble(downPaymentAmount);

                        if(fDpAmt > fApproveAmt)
                        {
                            fDpAmt = 0.0;
                            downPaymentAmount = "0";
                            txtFileDpAmount.setText("");
                            txtFileDpAmount.requestFocus();
                            txtFileDpAmount.setError("Down Payment Amount can't be greater than Approval Amount");
                        }
                        else
                        {
                            CalculateFileLoanAmount(fApproveAmt, fDpAmt);
                        }
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/*** METHOD TO EXECUTE FILE MODE OPERATION ***//*


    public void ExecuteFILEOperation()
    {
        try
        {
            // Set Visit Type = FILE
            visitTypeActual = Config.FILE_VISIT;


            // Get Values from the TextBox
            fileApprovalAmount    = txtFileApprovalAmount.getText().toString().trim();
            downPaymentAmount     = txtFileDpAmount.getText().toString().trim();
            fileLoanAmount        = txtFileLoanAmount.getText().toString().trim();
            fileInstallmentNo     = txtFileNoOfInstallment.getText().toString().trim();
            fileInstallmentAmount = txtFileInstallmentAmount.getText().toString().trim();
            customerMobile        = txtCustomerMobile.getText().toString().trim();

            calcLoanAmount        = txtLoanAmount.getText().toString().trim();
            calcEmi               = txtEmi.getText().toString().trim();
            calcRoi               = txtRoi.getText().toString().trim();
            calcTenure            = txtTenure.getText().toString().trim();

            loaneeResiProofOtherName = txtLoaneeResdProofOthers.getText().toString().trim().toUpperCase();
            loaneeIDProofOtherName   = txtLoaneeIDProofOthers.getText().toString().trim().toUpperCase();

            coApp1ResiProofOtherName = txtCoApp1ResdProofOthers.getText().toString().trim().toUpperCase();
            coApp1IDProofOtherName   = txtCoApp1IDProofOthers.getText().toString().trim().toUpperCase();

            coApp2ResiProofOtherName = txtCoApp2ResdProofOthers.getText().toString().trim().toUpperCase();
            coApp2IDProofOtherName   = txtCoApp2IDProofOthers.getText().toString().trim().toUpperCase();


            */
/**** For Loanee ****//*


            int rbLoaneeSelected = rbGrpLoanee.getCheckedRadioButtonId();
            rbLoanee     = (RadioButton) dialogFileMode.findViewById(rbLoaneeSelected);
            loaneeExist  = rbLoanee.getText().toString().trim();
            loaneeExist  = ResultofYesNoValue(loaneeExist);

            int rbLoaneePhotoSelected  = rbGrpLoaneePhoto.getCheckedRadioButtonId();
            rbLoaneePhoto = (RadioButton) dialogFileMode.findViewById(rbLoaneePhotoSelected);
            loaneePhoto   = rbLoaneePhoto.getText().toString().trim();
            loaneePhoto   = ResultofYesNoValue(loaneePhoto);

            int rbLoaneeResiProofSelected  = rbGrpLoaneeResdProof.getCheckedRadioButtonId();
            rbLoaneeResdProof = (RadioButton) dialogFileMode.findViewById(rbLoaneeResiProofSelected);
            loaneeResiProof   = rbLoaneeResdProof.getText().toString().trim();
            loaneeResiProof   = ResultofYesNoValue(loaneeResiProof);

            int rbLoaneeIDProofSelected  = rbGrpLoaneeIDProof.getCheckedRadioButtonId();
            rbLoaneeIDProof = (RadioButton) dialogFileMode.findViewById(rbLoaneeIDProofSelected);
            loaneeIDProof   = rbLoaneeIDProof.getText().toString().trim();
            loaneeIDProof   = ResultofYesNoValue(loaneeIDProof);

            int rbLoaneeSecurityChequeSelected  = rbGrpLoaneeSecurityCheque.getCheckedRadioButtonId();
            rbLoaneeSecurityCheque = (RadioButton) dialogFileMode.findViewById(rbLoaneeSecurityChequeSelected);
            loaneeSecurityCheque   = rbLoaneeSecurityCheque.getText().toString().trim();
            loaneeSecurityCheque   = ResultofYesNoValue(loaneeSecurityCheque);


            */
/**** For Co-Applicant-1 ****//*


            int rbCoApp1Selected = rbGrpCoApp1.getCheckedRadioButtonId();
            rbCoApp1     = (RadioButton) dialogFileMode.findViewById(rbCoApp1Selected);
            coApp1Exist  = rbCoApp1.getText().toString().trim();
            coApp1Exist  = ResultofYesNoValue(coApp1Exist);

            int rbCoApp1PhotoSelected  = rbGrpCoApp1Photo.getCheckedRadioButtonId();
            rbCoApp1Photo = (RadioButton) dialogFileMode.findViewById(rbCoApp1PhotoSelected);
            coApp1Photo   = rbCoApp1Photo.getText().toString().trim();
            coApp1Photo   = ResultofYesNoValue(coApp1Photo);

            int rbCoApp1ResiProofSelected  = rbGrpCoApp1ResdProof.getCheckedRadioButtonId();
            rbCoApp1ResdProof = (RadioButton) dialogFileMode.findViewById(rbCoApp1ResiProofSelected);
            coApp1ResiProof   = rbCoApp1ResdProof.getText().toString().trim();
            coApp1ResiProof   = ResultofYesNoValue(coApp1ResiProof);

            int rbCoApp1IDProofSelected  = rbGrpCoApp1IDProof.getCheckedRadioButtonId();
            rbCoApp1IDProof = (RadioButton) dialogFileMode.findViewById(rbCoApp1IDProofSelected);
            coApp1IDProof   = rbCoApp1IDProof.getText().toString().trim();
            coApp1IDProof   = ResultofYesNoValue(coApp1IDProof);

            int rbCoApp1SecurityChequeSelected  = rbGrpCoApp1SecurityCheque.getCheckedRadioButtonId();
            rbCoApp1SecurityCheque = (RadioButton) dialogFileMode.findViewById(rbCoApp1SecurityChequeSelected);
            coApp1SecurityCheque   = rbCoApp1SecurityCheque.getText().toString().trim();
            coApp1SecurityCheque   = ResultofYesNoValue(coApp1SecurityCheque);


            */
/**** For Co-Applicant-2 ****//*


            int rbCoApp2Selected = rbGrpCoApp2.getCheckedRadioButtonId();
            rbCoApp2     = (RadioButton) dialogFileMode.findViewById(rbCoApp2Selected);
            coApp2Exist  = rbCoApp2.getText().toString().trim();
            coApp2Exist  = ResultofYesNoValue(coApp2Exist);

            int rbCoApp2PhotoSelected  = rbGrpCoApp2Photo.getCheckedRadioButtonId();
            rbCoApp2Photo = (RadioButton) dialogFileMode.findViewById(rbCoApp2PhotoSelected);
            coApp2Photo   = rbCoApp2Photo.getText().toString().trim();
            coApp2Photo   = ResultofYesNoValue(coApp2Photo);

            int rbCoApp2ResiProofSelected  = rbGrpCoApp2ResdProof.getCheckedRadioButtonId();
            rbCoApp2ResdProof = (RadioButton) dialogFileMode.findViewById(rbCoApp2ResiProofSelected);
            coApp2ResiProof   = rbCoApp2ResdProof.getText().toString().trim();
            coApp2ResiProof   = ResultofYesNoValue(coApp2ResiProof);

            int rbCoApp2IDProofSelected  = rbGrpCoApp2IDProof.getCheckedRadioButtonId();
            rbCoApp2IDProof = (RadioButton) dialogFileMode.findViewById(rbCoApp2IDProofSelected);
            coApp2IDProof   = rbCoApp2IDProof.getText().toString().trim();
            coApp2IDProof   = ResultofYesNoValue(coApp2IDProof);

            int rbCoApp2SecurityChequeSelected  = rbGrpCoApp2SecurityCheque.getCheckedRadioButtonId();
            rbCoApp2SecurityCheque = (RadioButton) dialogFileMode.findViewById(rbCoApp2SecurityChequeSelected);
            coApp2SecurityCheque   = rbCoApp2SecurityCheque.getText().toString().trim();
            coApp2SecurityCheque   = ResultofYesNoValue(coApp2SecurityCheque);


            // For Loanee Resident Proof
            if(rbLoaneeResdProofYes.isChecked())
            {
                if(chLoaneeResdLightBill.isChecked())
                {
                    loaneeResiProofLightBill = Config.Y;
                }
                if(chLoaneeResdTaxBill.isChecked())
                {
                    loaneeResiProofTaxBill = Config.Y;
                }
                if(chLoaneeResdAadharCard.isChecked())
                {
                    loaneeResiProofAdharCard = Config.Y;
                }
                if(chLoaneeResdOther.isChecked())
                {
                    loaneeResiProofOther = Config.Y;
                }
                if(!chLoaneeResdLightBill.isChecked())
                {
                    loaneeResiProofLightBill = Config.N;
                }
                if(!chLoaneeResdTaxBill.isChecked())
                {
                    loaneeResiProofTaxBill = Config.N;
                }
                if(!chLoaneeResdAadharCard.isChecked())
                {
                    loaneeResiProofAdharCard = Config.N;
                }
                if(!chLoaneeResdOther.isChecked())
                {
                    loaneeResiProofOther = Config.N;
                    txtLoaneeResdProofOthers.setText("");
                    loaneeResiProofOtherName = "";
                }
            }

            // For Loanee ID Proof
            if(rbLoaneeIdProofYes.isChecked())
            {
                if(chLoaneeIDPanCard.isChecked())
                {
                    loaneeIDProofPanCard = Config.Y;
                }
                if(chLoaneeIDElectionCard.isChecked())
                {
                    loaneeIDProofElectionCard = Config.Y;
                }
                if(chLoaneeIDAadharCard.isChecked())
                {
                    loaneeIDProofAdharCard = Config.Y;
                }
                if(chLoaneeIDDrivingLicence.isChecked())
                {
                    loaneeIDProofDrivingLicense = Config.Y;
                }
                if(chLoaneeIDOther.isChecked())
                {
                    loaneeIDProofOther = Config.Y;
                }
                if(!chLoaneeIDPanCard.isChecked())
                {
                    loaneeIDProofPanCard = Config.N;
                }
                if(!chLoaneeIDElectionCard.isChecked())
                {
                    loaneeIDProofElectionCard = Config.N;
                }
                if(!chLoaneeIDAadharCard.isChecked())
                {
                    loaneeIDProofAdharCard = Config.N;
                }
                if(!chLoaneeIDDrivingLicence.isChecked())
                {
                    loaneeIDProofDrivingLicense = Config.N;
                }
                if(!chLoaneeIDOther.isChecked())
                {
                    loaneeIDProofOther = Config.N;
                    txtLoaneeIDProofOthers.setText("");
                    loaneeIDProofOtherName = "";
                }
            }

            // For Co-Applicant 1 Resident Proof
            if(rbCoApp1ResdProofYes.isChecked())
            {
                if(chCoApp1ResdLightBill.isChecked())
                {
                    coApp1ResiProofLightBill = Config.Y;
                }
                if(chCoApp1ResdTaxBill.isChecked())
                {
                    coApp1ResiProofTaxBill = Config.Y;
                }
                if(chCoApp1ResdAadharCard.isChecked())
                {
                    coApp1ResiProofAdharCard = Config.Y;
                }
                if(chCoApp1ResdOther.isChecked())
                {
                    coApp1ResiProofOther = Config.Y;
                }
                if(!chCoApp1ResdLightBill.isChecked())
                {
                    coApp1ResiProofLightBill = Config.N;
                }
                if(!chCoApp1ResdTaxBill.isChecked())
                {
                    coApp1ResiProofTaxBill = Config.N;
                }
                if(!chCoApp1ResdAadharCard.isChecked())
                {
                    coApp1ResiProofAdharCard = Config.N;
                }
                if(!chCoApp1ResdOther.isChecked())
                {
                    coApp1ResiProofOther = Config.N;
                    txtCoApp1ResdProofOthers.setText("");
                    coApp1ResiProofOtherName = "";
                }
            }

            // For Co-Applicant 1 ID Proof
            if(rbCoApp1IdProofYes.isChecked())
            {
                if(chCoApp1IDPanCard.isChecked())
                {
                    coApp1IDProofPanCard = Config.Y;
                }
                if(chCoApp1IDElectionCard.isChecked())
                {
                    coApp1IDProofElectionCard = Config.Y;
                }
                if(chCoApp1IDAadharCard.isChecked())
                {
                    coApp1IDProofAdharCard = Config.Y;
                }
                if(chCoApp1IDDrivingLicence.isChecked())
                {
                    coApp1IDProofDrivingLicense = Config.Y;
                }
                if(chCoApp1IDOther.isChecked())
                {
                    coApp1IDProofOther = Config.Y;
                }
                if(!chCoApp1IDPanCard.isChecked())
                {
                    coApp1IDProofPanCard = Config.N;
                }
                if(!chCoApp1IDElectionCard.isChecked())
                {
                    coApp1IDProofElectionCard = Config.N;
                }
                if(!chCoApp1IDAadharCard.isChecked())
                {
                    coApp1IDProofAdharCard = Config.N;
                }
                if(!chCoApp1IDDrivingLicence.isChecked())
                {
                    coApp1IDProofDrivingLicense = Config.N;
                }
                if(!chCoApp1IDOther.isChecked())
                {
                    coApp1IDProofOther = Config.N;
                    txtCoApp1IDProofOthers.setText("");
                    coApp1IDProofOtherName = "";
                }
            }

            // For Co-Applicant 2 Resident Proof
            if(rbCoApp2ResdProofYes.isChecked())
            {
                if(chCoApp2ResdLightBill.isChecked())
                {
                    coApp2ResiProofLightBill = Config.Y;
                }
                if(chCoApp2ResdTaxBill.isChecked())
                {
                    coApp2ResiProofTaxBill = Config.Y;
                }
                if(chCoApp2ResdAadharCard.isChecked())
                {
                    coApp2ResiProofAdharCard = Config.Y;
                }
                if(chCoApp2ResdOther.isChecked())
                {
                    coApp2ResiProofOther = Config.Y;
                }
                if(!chCoApp2ResdLightBill.isChecked())
                {
                    coApp2ResiProofLightBill = Config.N;
                }
                if(!chCoApp2ResdTaxBill.isChecked())
                {
                    coApp2ResiProofTaxBill = Config.N;
                }
                if(!chCoApp2ResdAadharCard.isChecked())
                {
                    coApp2ResiProofAdharCard = Config.N;
                }
                if(!chCoApp2ResdOther.isChecked())
                {
                    coApp2ResiProofOther = Config.N;
                    txtCoApp2ResdProofOthers.setText("");
                    coApp2ResiProofOtherName = "";
                }
            }

            // For Co-Applicant 2 ID Proof
            if(rbCoApp2IdProofYes.isChecked())
            {
                if(chCoApp2IDPanCard.isChecked())
                {
                    coApp2IDProofPanCard = Config.Y;
                }
                if(chCoApp2IDElectionCard.isChecked())
                {
                    coApp2IDProofElectionCard = Config.Y;
                }
                if(chCoApp2IDAadharCard.isChecked())
                {
                    coApp2IDProofAdharCard = Config.Y;
                }
                if(chCoApp2IDDrivingLicence.isChecked())
                {
                    coApp2IDProofDrivingLicense = Config.Y;
                }
                if(chCoApp2IDOther.isChecked())
                {
                    coApp2IDProofOther = Config.Y;
                }
                if(!chCoApp2IDPanCard.isChecked())
                {
                    coApp2IDProofPanCard = Config.N;
                }
                if(!chCoApp2IDElectionCard.isChecked())
                {
                    coApp2IDProofElectionCard = Config.N;
                }
                if(!chCoApp2IDAadharCard.isChecked())
                {
                    coApp2IDProofAdharCard = Config.N;
                }
                if(!chCoApp2IDDrivingLicence.isChecked())
                {
                    coApp2IDProofDrivingLicense = Config.N;
                }
                if(!chCoApp2IDOther.isChecked())
                {
                    coApp2IDProofOther = Config.N;
                    txtCoApp2IDProofOthers.setText("");
                    coApp2IDProofOtherName = "";
                }
            }

            // If Loanee Resident Proof is not Checked
            if(rbLoaneeResdProofNo.isChecked())
            {
                loaneeResiProofLightBill = Config.N;
                loaneeResiProofTaxBill   = Config.N;
                loaneeResiProofAdharCard = Config.N;
                loaneeResiProofOther     = Config.N;
                loaneeResiProofOtherName = "";
                txtLoaneeResdProofOthers.setText("");
            }

            // If Loanee ID Proof is not Checked
            if(rbLoaneeIdProofNo.isChecked())
            {
                loaneeIDProofPanCard        = Config.N;
                loaneeIDProofElectionCard   = Config.N;
                loaneeIDProofAdharCard      = Config.N;
                loaneeIDProofDrivingLicense = Config.N;
                loaneeIDProofOther          = Config.N;
                loaneeIDProofOtherName      = "";
                txtLoaneeIDProofOthers.setText("");
            }

            // If Co-Applicant 1 Resident Proof is not Checked
            if(rbCoApp1ResdProofNo.isChecked())
            {
                coApp1ResiProofLightBill = Config.N;
                coApp1ResiProofTaxBill   = Config.N;
                coApp1ResiProofAdharCard = Config.N;
                coApp1ResiProofOther     = Config.N;
                coApp1ResiProofOtherName = "";
                txtCoApp1ResdProofOthers.setText("");
            }

            // If Co-Applicant 1 ID Proof is not Checked
            if(rbCoApp1IdProofNo.isChecked())
            {
                coApp1IDProofPanCard        = Config.N;
                coApp1IDProofElectionCard   = Config.N;
                coApp1IDProofAdharCard      = Config.N;
                coApp1IDProofDrivingLicense = Config.N;
                coApp1IDProofOther          = Config.N;
                coApp1IDProofOtherName      = "";
                txtCoApp1IDProofOthers.setText("");
            }

            // If Co-Applicant 2 Resident Proof is not Checked
            if(rbCoApp2ResdProofNo.isChecked())
            {
                coApp2ResiProofLightBill = Config.N;
                coApp2ResiProofTaxBill   = Config.N;
                coApp2ResiProofAdharCard = Config.N;
                coApp2ResiProofOther     = Config.N;
                coApp2ResiProofOtherName = "";
                txtCoApp2ResdProofOthers.setText("");
            }

            // If Co-Applicant 2 ID Proof is not Checked
            if(rbCoApp2IdProofNo.isChecked())
            {
                coApp2IDProofPanCard        = Config.N;
                coApp2IDProofElectionCard   = Config.N;
                coApp2IDProofAdharCard      = Config.N;
                coApp2IDProofDrivingLicense = Config.N;
                coApp2IDProofOther          = Config.N;
                coApp2IDProofOtherName      = "";
                txtCoApp2IDProofOthers.setText("");
            }

            // Check for Validation
            if(fileApprovalAmount.isEmpty())
            {
                txtFileApprovalAmount.requestFocus();
                txtFileApprovalAmount.setError("Enter File Approval Amount");
            }
            else if(downPaymentAmount.isEmpty())
            {
                txtFileDpAmount.requestFocus();
                txtFileDpAmount.setError("Enter Down Payment Amount");
            }
            else if(fileLoanAmount.isEmpty())
            {
                txtFileLoanAmount.requestFocus();
                txtFileLoanAmount.setError("Enter File Loan Amount");
            }
            else if(fileInstallmentNo.isEmpty())
            {
                txtFileNoOfInstallment.requestFocus();
                txtFileNoOfInstallment.setError("Enter No. of Installment");
            }
            else if(fileInstallmentAmount.isEmpty())
            {
                txtFileInstallmentAmount.requestFocus();
                txtFileInstallmentAmount.setError("Enter Installment Amount");
            }
            else if(chLoaneeResdOther.isChecked() && loaneeResiProofOtherName.isEmpty())
            {
                txtLoaneeResdProofOthers.requestFocus();
                txtLoaneeResdProofOthers.setError("Enter Other Resident Proof Document");
            }
            else if(chLoaneeIDOther.isChecked() && loaneeIDProofOtherName.isEmpty())
            {
                txtLoaneeIDProofOthers.requestFocus();
                txtLoaneeIDProofOthers.setError("Enter Other ID Proof Document");
            }
            else if(chCoApp1ResdOther.isChecked() && coApp1ResiProofOtherName.isEmpty())
            {
                txtCoApp1ResdProofOthers.requestFocus();
                txtCoApp1ResdProofOthers.setError("Enter Other Resident Proof Document");
            }
            else if(chCoApp1IDOther.isChecked() && coApp1IDProofOtherName.isEmpty())
            {
                txtCoApp1IDProofOthers.requestFocus();
                txtCoApp1IDProofOthers.setError("Enter Other ID Proof Document");
            }
            else if(chCoApp2ResdOther.isChecked() && coApp2ResiProofOtherName.isEmpty())
            {
                txtCoApp2ResdProofOthers.requestFocus();
                txtCoApp2ResdProofOthers.setError("Enter Other Resident Proof Document");
            }
            else if(chCoApp2IDOther.isChecked() && coApp2IDProofOtherName.isEmpty())
            {
                txtCoApp2IDProofOthers.requestFocus();
                txtCoApp2IDProofOthers.setError("Enter Other ID Proof Document");
            }
            else
            {
                // If Loan Amount is Checked
                if(rbLoanAmt.isChecked())
                {
                    if(calcEmi.isEmpty())
                    {
                        txtEmi.requestFocus();
                        txtEmi.setError("Enter EMI Value");
                    }
                    else if(calcRoi.isEmpty())
                    {
                        txtRoi.requestFocus();
                        txtRoi.setError("Enter Rate of Interest");
                    }
                    else if(calcTenure.isEmpty())
                    {
                        txtTenure.requestFocus();
                        txtTenure.setError("Enter Tenure");
                    }
                    else if(calcLoanAmount.isEmpty())
                    {
                        txtLoanAmount.requestFocus();
                        txtLoanAmount.setError("Click on Calculate before Submit your data");
                    }
                    else if(!customerMobile.isEmpty() && customerMobile.length() >= 0 && customerMobile.length() <= 10)
                    {
                        if(!customerMobile.matches(Config.MOBILE_VALIDATOR))
                        {
                            txtCustomerMobile.requestFocus();
                            txtCustomerMobile.setError("Enter Valid Mobile Number");
                        }
                        else
                        {
                            SendFILEDataOnServer();
                        }
                    }
                    else if(customerMobile.isEmpty() || customerMobile.length() <= 0)
                    {
                        customerMobile = "";
                        SendFILEDataOnServer();
                    }
                    else
                    {
                        SendFILEDataOnServer();
                    }
                }

                // If EMI is Checked
                if(rbEmi.isChecked())
                {
                    if(calcLoanAmount.isEmpty())
                    {
                        txtLoanAmount.requestFocus();
                        txtLoanAmount.setError("Enter Loan Amount");
                    }
                    else if(calcRoi.isEmpty())
                    {
                        txtRoi.requestFocus();
                        txtRoi.setError("Enter Rate of Interest");
                    }
                    else if(calcTenure.isEmpty())
                    {
                        txtTenure.requestFocus();
                        txtTenure.setError("Enter Tenure");
                    }
                    else if(calcEmi.isEmpty())
                    {
                        txtEmi.requestFocus();
                        txtEmi.setError("Click on Calculate before Submit your data");
                    }
                    else if(!customerMobile.isEmpty() && customerMobile.length() >= 0 && customerMobile.length() <= 10)
                    {
                        if(!customerMobile.matches(Config.MOBILE_VALIDATOR))
                        {
                            txtCustomerMobile.requestFocus();
                            txtCustomerMobile.setError("Enter Valid Mobile Number");
                        }
                        else
                        {
                            SendFILEDataOnServer();
                        }
                    }
                    else if(customerMobile.isEmpty() || customerMobile.length() <= 0)
                    {
                        customerMobile = "";
                        SendFILEDataOnServer();
                    }
                    else
                    {
                        SendFILEDataOnServer();
                    }
                }

                // If ROI is Checked
                if(rbRoi.isChecked())
                {
                    if(calcLoanAmount.isEmpty())
                    {
                        txtLoanAmount.requestFocus();
                        txtLoanAmount.setError("Enter Loan Amount");
                    }
                    else if(calcEmi.isEmpty())
                    {
                        txtEmi.requestFocus();
                        txtEmi.setError("Enter EMI Value");
                    }
                    else if(calcTenure.isEmpty())
                    {
                        txtTenure.requestFocus();
                        txtTenure.setError("Enter Tenure");
                    }
                    else if(calcRoi.isEmpty())
                    {
                        txtRoi.requestFocus();
                        txtRoi.setError("Click on Calculate before Submit your data");
                    }
                    else if(!customerMobile.isEmpty() && customerMobile.length() >= 0 && customerMobile.length() <= 10)
                    {
                        if(!customerMobile.matches(Config.MOBILE_VALIDATOR))
                        {
                            txtCustomerMobile.requestFocus();
                            txtCustomerMobile.setError("Enter Valid Mobile Number");
                        }
                        else
                        {
                            SendFILEDataOnServer();
                        }
                    }
                    else if(customerMobile.isEmpty() || customerMobile.length() <= 0)
                    {
                        customerMobile = "";
                        SendFILEDataOnServer();
                    }
                    else
                    {
                        SendFILEDataOnServer();
                    }
                }

                // If Tenure is Checked
                if(rbTenure.isChecked())
                {
                    if(calcLoanAmount.isEmpty())
                    {
                        txtLoanAmount.requestFocus();
                        txtLoanAmount.setError("Enter Loan Amount");
                    }
                    else if(calcEmi.isEmpty())
                    {
                        txtEmi.requestFocus();
                        txtEmi.setError("Enter EMI Value");
                    }
                    else if(calcRoi.isEmpty())
                    {
                        txtRoi.requestFocus();
                        txtRoi.setError("Enter Rate of Interest");
                    }
                    else if(calcTenure.isEmpty())
                    {
                        txtTenure.requestFocus();
                        txtTenure.setError("Click on Calculate before Submit your data");
                    }
                    else if(!customerMobile.isEmpty() && customerMobile.length() >= 0 && customerMobile.length() <= 10)
                    {
                        if(!customerMobile.matches(Config.MOBILE_VALIDATOR))
                        {
                            txtCustomerMobile.requestFocus();
                            txtCustomerMobile.setError("Enter Valid Mobile Number");
                        }
                        else
                        {
                            SendFILEDataOnServer();
                        }
                    }
                    else if(customerMobile.isEmpty() || customerMobile.length() <= 0)
                    {
                        customerMobile = "";
                        SendFILEDataOnServer();
                    }
                    else
                    {
                        SendFILEDataOnServer();
                    }
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/** METHOD TO SEND FILE MODE DATA **//*


    public void SendFILEDataOnServer()
    {
        try
        {
            // Get Current DateTime
            entryDate = CommonFunctions.GetCurrentDateTime(Config.SOAP_DATE_FORMAT);


            // If USER ACCESS IS ONLINE
            if(userAccess == 1)
            {
                if(checkNetwork.isInternetAvailable(getApplicationContext()))
                {
                    DialogConfig.getInstance().StartProgressDialog(FieldVisitCustomerDetailsPlanned.this, Config.PLEASE_WAIT);

                    final Thread myThread = new Thread()
                    {
                        @Override
                        public void run()
                        {
                            super.run();

                            try
                            {
                                // Call Method to Save PTP Mode Visit Collection Data on Server
                                SaveAndUpdateVisitCollectionDataONLINE(Integer.parseInt(Config.FILE_VISIT));
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    };

                    myThread.start();
                }
                else
                {
                    DialogConfig.getInstance().ShowInternetDialog(FieldVisitCustomerDetailsPlanned.this);
                }
            }

            // If USER ACCESS IS OFFLINE
            if(userAccess == 0)
            {
                // Call Method to Save PTP Mode Visit Collection Data on Server
                SaveAndUpdateVisitCollectionDataOFFLINE(Integer.parseInt(Config.FILE_VISIT));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/** METHOD TO CONVERT YES/NO TO Y/N **//*


    public String ResultofYesNoValue(String value)
    {
        String result = "";

        if(!value.isEmpty() || !value.equals("null") || value != null)
        {
            if(value.equals(Config.YES))
            {
                result = Config.Y;
            }

            if(value.equals(Config.NO))
            {
                result = Config.N;
            }
        }
        else
        {
            result = Config.N;
        }

        return result;
    }


    */
/** METHOD TO CLEAR TEXT BOX VALUE OF LOAN CALCULAOR IN FILE DIALOG **//*


    public void EmptyPtpDialogTextValues()
    {
        txtCashPtpDate.setText("");
        txtCashPtpTime.setText("");
        txtCashPtpAmount.setText("");

        txtFilePtpDate.setText("");
        txtFileApprovalAmount.setText("");
        txtFileDpAmount.setText("");
        txtFileLoanAmount.setText("");
        txtFileNoOfInstallment.setText("");
        txtFileInstallmentAmount.setText("");
        txtLoanAmount.setText("");
        txtEmi.setText("");
        txtRoi.setText("");
        txtTenure.setText("");

        txtSeizePtpDate.setText("");
        txtVehicleName.setText("");
        txtVehicleNo.setText("");
        txtEngineNo.setText("");
        txtChasisNo.setText("");
        txtPoliceStationName.setText("");
        txtPolicStationCenter.setText("");
        txtSeizeAgencyName.setText("");
        txtPoliceStationFAX.setText("");
        txtPoliceStationEmail.setText("");
    }


    */
/** METHOD TO CLEAR TEXT BOX VALUE OF LOAN CALCULAOR IN FILE DIALOG**//*


    public void emptyLoanCalcTextValue()
    {
        txtLoanAmount.setText("");
        txtEmi.setText("");
        txtRoi.setText("");
        txtTenure.setText("");
    }


    */
/** METHOD TO CALCULATE TOTAL FILE LOAN AMOUNT **//*


    public void CalculateFileLoanAmount(double approveAmt, double dpAmt)
    {
        fLoanAmt = 0.0;
        fLoanAmt = approveAmt - dpAmt;
        txtFileLoanAmount.setText(String.format("%.0f",fLoanAmt));
    }


    */
/*** METHOD TO SHOW SEIZE DIALOG ***//*


    public void DisplaySeizeDialog()
    {
        dialogSeizeMode = new Dialog(FieldVisitCustomerDetailsPlanned.this);
        dialogSeizeMode.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogSeizeMode.setContentView(R.layout.dialog_new_seize_mode);
        dialogSeizeMode.setCancelable(true);

        // Initialize Components

        rbGrpPriorInfo   = (RadioGroup) dialogSeizeMode.findViewById(R.id.rbGrpPriorInfoPolice);
        rbGrpPostInfo    = (RadioGroup) dialogSeizeMode.findViewById(R.id.rbGrpPostInfoPolice);

        rbPriorInfoYes   = (RadioButton) dialogSeizeMode.findViewById(R.id.rbPriorInfoPoliceYes);
        rbPriorInfoNo    = (RadioButton) dialogSeizeMode.findViewById(R.id.rbPriorInfoPoliceNo);
        rbPostInfoYes    = (RadioButton) dialogSeizeMode.findViewById(R.id.rbPostInfoPoliceYes);
        rbPostInfoNo     = (RadioButton) dialogSeizeMode.findViewById(R.id.rbPostInfoPoliceNo);

        btnSeizeSubmit   = (Button) dialogSeizeMode.findViewById(R.id.btnSeizeSubmit);


        */
/*** METHOD TO PERFORM ONCLICK EVENT OF SUBMIT BUTTON IN SEIZE MODE OPERATION ***//*


        btnSeizeSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    new AlertDialog.Builder(FieldVisitCustomerDetailsPlanned.this)
                            .setIcon(R.mipmap.dialogimg)
                            .setTitle(Config.CONFIRMATION_TITLE)
                            .setMessage(Config.CONFIRMATION_MESSAGE)
                            .setPositiveButton(Config.YES, new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    ExecuteSeizeModeOperation();
                                }

                            })
                            .setNegativeButton(Config.NO, null)
                            .show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });


        dialogSeizeMode.show();
        Window window = dialogSeizeMode.getWindow();
        assert window != null;
        window.setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
    }


    */
/*** METHOD TO EXECUTE SEIZE MODE OPERATION ***//*


    public void ExecuteSeizeModeOperation()
    {
        try
        {
            int isPriorInfoGiven = rbGrpPriorInfo.getCheckedRadioButtonId();
            rbPriorInfoGiven     = (RadioButton) dialogSeizeMode.findViewById(isPriorInfoGiven);
            priorInfoPolice      = rbPriorInfoGiven.getText().toString().trim();
            priorInfoPolice      = String.valueOf(SeizeYesNoResult(priorInfoPolice));

            int isPostInfoGiven  = rbGrpPostInfo.getCheckedRadioButtonId();
            rbPostInfoGiven      = (RadioButton) dialogSeizeMode.findViewById(isPostInfoGiven);
            postInfoPolice       = rbPostInfoGiven.getText().toString().trim();
            postInfoPolice      = String.valueOf(SeizeYesNoResult(postInfoPolice));


            // Set Visit Type = SEIZE
            visitTypeActual = Config.SEIZE_VISIT;

            // Get Current DateTime
            entryDate = CommonFunctions.GetCurrentDateTime(Config.SOAP_DATE_FORMAT);


            // If USER ACCESS IS ONLINE
            if(userAccess == 1)
            {
                if(checkNetwork.isInternetAvailable(getApplicationContext()))
                {
                    DialogConfig.getInstance().StartProgressDialog(FieldVisitCustomerDetailsPlanned.this, Config.PLEASE_WAIT);

                    final Thread myThread = new Thread()
                    {
                        @Override
                        public void run()
                        {
                            super.run();

                            try
                            {
                                // Call Method to Save PTP Mode Visit Collection Data on Server
                                SaveAndUpdateVisitCollectionDataONLINE(Integer.parseInt(Config.SEIZE_VISIT));
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    };

                    myThread.start();
                }
                else
                {
                    DialogConfig.getInstance().ShowInternetDialog(FieldVisitCustomerDetailsPlanned.this);
                }
            }

            // If USER ACCESS IS OFFLINE
            if(userAccess == 0)
            {
                // Call Method to Save PTP Mode Visit Collection Data on Server
                SaveAndUpdateVisitCollectionDataOFFLINE(Integer.parseInt(Config.SEIZE_VISIT));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/** METHOD TO RETURN INTEGER VALUE OF SEIZE MODE RESULT **//*


    public int SeizeYesNoResult(String value)
    {
        int result = 0;

        if(!value.isEmpty() || !value.equals("null") || value != null)
        {
            if(value.equals(Config.YES))
            {
                result = 1;
            }

            if(value.equals(Config.NO))
            {
                result = 0;
            }
        }
        else
        {
            result = 0;
        }

        return result;
    }


    */
/*** METHOD TO SHOW REMARK DIALOG ***//*


    public void DisplayRemarkDialog()
    {
        dialogRemarkMode = new Dialog(FieldVisitCustomerDetailsPlanned.this);
        dialogRemarkMode.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogRemarkMode.setContentView(R.layout.dialog_new_remark_mode);
        dialogRemarkMode.setCancelable(true);

        spRemark = (Spinner) dialogRemarkMode.findViewById(R.id.spinnerRemark);
        ArrayAdapter<String> aReamrk = new ArrayAdapter<String>(getApplicationContext(),R.layout.custom_spinner_item, GetFeedBackListData());
        aReamrk.setDropDownViewResource(R.layout.custom_spinner_popup);
        spRemark.setAdapter(aReamrk);

        spRemark.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                switch (adapterView.getId())
                {
                    case R.id.spinnerRemark:
                        spRemark.setSelection(i);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}

        });


        btnRemarkSubmit = (Button)dialogRemarkMode.findViewById(R.id.btnRemarkSubmit);


        */
/*** METHOD TO PERFORM ONCLICK EVENT OF SUBMIT BUTTON IN REMARK MODE OPERATION ***//*


        btnRemarkSubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                try
                {
                    new AlertDialog.Builder(FieldVisitCustomerDetailsPlanned.this)
                            .setIcon(R.mipmap.dialogimg)
                            .setTitle(Config.CONFIRMATION_TITLE)
                            .setMessage(Config.CONFIRMATION_MESSAGE)
                            .setPositiveButton(Config.YES, new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    ExecuteRemarkModeOperation();
                                }

                            })
                            .setNegativeButton(Config.NO, null)
                            .show();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        dialogRemarkMode.show();
        Window window = dialogRemarkMode.getWindow();
        assert window != null;
        window.setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
    }


    */
/** METHOD TO EXECUTE REMARK MODE OPERATION **//*


    public void ExecuteRemarkModeOperation()
    {
        try
        {
            // Set Visit Type = REMARK
            visitTypeActual = Config.REMARK_VISIT;

            // Get Current DateTime
            entryDate = CommonFunctions.GetCurrentDateTime(Config.SOAP_DATE_FORMAT);

            remarkId = spRemark.getSelectedItem().toString();

            for(int i = 0; i < feedBackList.size(); i++)
            {
                HashMap<String, String> clist = feedBackList.get(i);
                String tId   = clist.get("Id");
                String tName = clist.get("Feedback");

                if(!tName.isEmpty() && tName.equals(remarkId))
                {
                    remarkId = tId;
                }
            }


            // If USER ACCESS IS ONLINE
            if(userAccess == 1)
            {
                if(checkNetwork.isInternetAvailable(getApplicationContext()))
                {
                    DialogConfig.getInstance().StartProgressDialog(FieldVisitCustomerDetailsPlanned.this, Config.PLEASE_WAIT);

                    final Thread myThread = new Thread()
                    {
                        @Override
                        public void run()
                        {
                            super.run();

                            try
                            {
                                // Call Method to Save PTP Mode Visit Collection Data on Server
                                SaveAndUpdateVisitCollectionDataONLINE(Integer.parseInt(Config.REMARK_VISIT));
                            }
                            catch (Exception e)
                            {
                                e.printStackTrace();
                            }
                        }
                    };

                    myThread.start();
                }
                else
                {
                    DialogConfig.getInstance().ShowInternetDialog(FieldVisitCustomerDetailsPlanned.this);
                }
            }

            // If USER ACCESS IS OFFLINE
            if(userAccess == 0)
            {
                // Call Method to Save PTP Mode Visit Collection Data on Server
                SaveAndUpdateVisitCollectionDataOFFLINE(Integer.parseInt(Config.REMARK_VISIT));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/** METHOD TO REDIRECT TO FACT_SHEET ACTIVITY **//*


    public void OpenFactSheetActivity()
    {
        DialogConfig.getInstance().StopProgressDialog();

        Intent intent = new Intent(getApplicationContext(), FactSheet.class);
        intent.putExtra("ExecutiveCode", execCode);
        intent.putExtra("UserId", userId);
        intent.putExtra("ImeiNumber", ImeiNo);
        intent.putExtra("TrgMasterId", trgMastId);
        intent.putExtra("CustCode", customerCode);
        intent.putExtra("PlanVisitId", planVisitId);
        intent.putExtra("CustomerName", custName);
        intent.putExtra("UserAccess", String.valueOf(userAccess));
        startActivity(intent);
        finish();
    }


    */
/** METHOD TO EXECUTE COLLECTION MODE OPERATION **//*


    public void ExecuteCollectionModeOperation()
    {
        new AlertDialog.Builder(FieldVisitCustomerDetailsPlanned.this)
                .setIcon(R.mipmap.dialogimg)
                .setTitle(Config.CONFIRMATION_TITLE)
                .setMessage(Config.CONFIRMATION_MESSAGE)
                .setPositiveButton(Config.YES, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent intent = new Intent(getApplicationContext(), CollectionTypeMainPlanned.class);
                        intent.putExtra("ExecutiveCode", execCode);
                        intent.putExtra("UserId", userId);
                        intent.putExtra("ImeiNumber", ImeiNo);
                        intent.putExtra("TrgMasterId", trgMastId);
                        intent.putExtra("CustCode", customerCode);
                        intent.putExtra("PlanVisitId", planVisitId);
                        intent.putExtra("CustomerName", custName);
                        intent.putExtra("UserAccess", String.valueOf(userAccess));
                        startActivity(intent);
                        finish();
                    }

                })
                .setNegativeButton(Config.NO, null)
                .show();
    }


    */
/** METHOD TO SAVE & UPDATE VISIT_COLLECTION DATA ( FOR ONLINE ACCESS ) **//*


    public void SaveAndUpdateVisitCollectionDataONLINE(final int visitId)
    {
        try
        {
            switch (visitId)
            {
                case 2:

                    EmptyText_Except_PTP_Mode();

                    break;

                case 3:

                    EmptyText_Except_SEIZE_Mode();

                    break;

                case 4:

                    EmptyText_Except_FILE_Mode();

                    break;

                case 5:

                    EmptyText_Except_REMARK_Mode();

                    break;
            }


            visitCollObj = new UpdateDataService(VISIT_COLLECTION_SERVICE);

            visitCollObj.CallServiceObject(VISIT_COLLECTION_ACTION_3, SAVE_NEW_VISIT_COLLECTION, VISIT_COLLECTION_OBJ,
                    "bus:Amount", cashAmount,
                    "bus:Bankaddress", bankAddress,
                    "bus:Bankname", bankName,
                    "bus:Bid", bId,
                    "bus:CenterOfPoliceStation", seizePoliceStationCenter,
                    "bus:ChasisNo", seizeChasisNo,
                    "bus:Chequeno", chequeNo,
                    "bus:Cid", cId,
                    "bus:Coapp1Exist", coApp1Exist,
                    "bus:Coapp1IdProof", coApp1IDProof,
                    "bus:Coapp1IdProofAadharCard", coApp1IDProofAdharCard,
                    "bus:Coapp1IdProofDrivingLicense", coApp1IDProofDrivingLicense,
                    "bus:Coapp1IdProofElectionCard", coApp1IDProofElectionCard,
                    "bus:Coapp1IdProofOther", coApp1IDProofOther,
                    "bus:Coapp1IdProofOtherName", coApp1IDProofOtherName,
                    "bus:Coapp1IdProofPanCard", coApp1IDProofPanCard,
                    "bus:Coapp1Photo", coApp1Photo,
                    "bus:Coapp1ResiProof", coApp1ResiProof,
                    "bus:Coapp1ResiProofAadharCard", coApp1ResiProofAdharCard,
                    "bus:Coapp1ResiProofLightBill", coApp1ResiProofLightBill,
                    "bus:Coapp1ResiProofOther", coApp1ResiProofOther,
                    "bus:Coapp1ResiProofOtherName", coApp1ResiProofOtherName,
                    "bus:Coapp1ResiProofTaxBill", coApp1ResiProofTaxBill,
                    "bus:Coapp1SecurityCheque", coApp1SecurityCheque,
                    "bus:Coapp2Exist", coApp2Exist,
                    "bus:Coapp2IdProof", coApp2IDProof,
                    "bus:Coapp2IdProofAadharCard", coApp2IDProofAdharCard,
                    "bus:Coapp2IdProofDrivingLicense", coApp2IDProofDrivingLicense,
                    "bus:Coapp2IdProofElectionCard", coApp2IDProofElectionCard,
                    "bus:Coapp2IdProofOther", coApp2IDProofOther,
                    "bus:Coapp2IdProofOtherName", coApp2IDProofOtherName,
                    "bus:Coapp2IdProofPanCard", coApp2IDProofPanCard,
                    "bus:Coapp2Photo", coApp2Photo,
                    "bus:Coapp2ResiProof", coApp2ResiProof,
                    "bus:Coapp2ResiProofAadharCard", coApp2ResiProofAdharCard,
                    "bus:Coapp2ResiProofLightBill", coApp2ResiProofLightBill,
                    "bus:Coapp2ResiProofOther", coApp2ResiProofOther,
                    "bus:Coapp2ResiProofTaxBill", coApp2ResiProofTaxBill,
                    "bus:Coapp2ResiRpoofOtherName", coApp2ResiProofOtherName,
                    "bus:Coapp2SecurityCheque", coApp2SecurityCheque,
                    "bus:CollectionLatitude", collectionLatitude,
                    "bus:CollectionLongitude", collectionLongitude,
                    "bus:CustomerMobile", customerMobile,
                    "bus:Ddno", ddNo,
                    "bus:DownPaymentAmt", downPaymentAmount,
                    "bus:EmailPoliceStation", seizePoliceStationEmail,
                    "bus:Emi", calcEmi,
                    "bus:EngineNo", seizeEngineNo,
                    "bus:Entrydate", entryDate,
                    "bus:FaxNoPoliceStation", seizePoliceStationFax,
                    "bus:FileApprovalAmt", fileApprovalAmount,
                    "bus:FileInstAmt", fileInstallmentAmount,
                    "bus:FileInstNo", fileInstallmentNo,
                    "bus:FileLoanAmt", fileLoanAmount,
                    "bus:Ifsccode", ifscCode,
                    "bus:Isactive", isActive,
                    "bus:Isdelete", isDelete,
                    "bus:Issync", isSync,
                    "bus:LoanAmt", calcLoanAmount,
                    "bus:LoaneeExist", loaneeExist,
                    "bus:LoaneeIdProof", loaneeIDProof,
                    "bus:LoaneeIdProofAadharCard", loaneeIDProofAdharCard,
                    "bus:LoaneeIdProofDrivingLicense", loaneeIDProofDrivingLicense,
                    "bus:LoaneeIdProofElectionCard", loaneeIDProofElectionCard,
                    "bus:LoaneeIdProofOther", loaneeIDProofOther,
                    "bus:LoaneeIdProofOtherName", loaneeIDProofOtherName,
                    "bus:LoaneeIdProofPanCard", loaneeIDProofPanCard,
                    "bus:LoaneePhoto", loaneePhoto,
                    "bus:LoaneeResiProof", loaneeResiProof,
                    "bus:LoaneeResiProofAadharCard", loaneeResiProofAdharCard,
                    "bus:LoaneeResiProofLightBill", loaneeResiProofLightBill,
                    "bus:LoaneeResiProofOther", loaneeResiProofOther,
                    "bus:LoaneeResiProofOtherName", loaneeResiProofOtherName,
                    "bus:LoaneeResiProofTaxBill", loaneeResiProofTaxBill,
                    "bus:LoaneeSecurityCheque", loaneeSecurityCheque,
                    "bus:Micrcode", micrCode,
                    "bus:NameOfPoliceStation", seizePoliceStationName,
                    "bus:Notes", notes,
                    "bus:PaymentType", paymentType,
                    "bus:PlanVisitId", planVisitId,
                    "bus:PostInfoToPolice", postInfoPolice,
                    "bus:PriorInfoToPolice", priorInfoPolice,
                    "bus:PtpAmt", ptpModeAmount,
                    "bus:PtpDate", ptpModeDate,
                    "bus:PtpMode", ptpMode,
                    "bus:PtpTime", ptpModeTime,
                    "bus:RateOfInterest", calcRoi,
                    "bus:Remarkid", remarkId,
                    "bus:SeizingAgencyName", seizingAgencyName,
                    "bus:Sid", sId,
                    "bus:Status", status,
                    "bus:Tenure", calcTenure,
                    "bus:TransactionDate", transactionDate,
                    "bus:VehicleName", seizeVehicleName,
                    "bus:VehicleNo", seizeVehicleNo,
                    "bus:VehiclePhoto1", vehiclePhoto1,
                    "bus:VehiclePhoto2", vehiclePhoto2,
                    "bus:VehiclePhoto3", vehiclePhoto3,
                    "bus:VisitTypeActual", visitTypeActual);

            visitCollectionId = (String) visitCollObj.getVariableValue("SaveNewVisitCollectionResult", String.class);

            */
/**** SAVE VISIT_COLLECTION HISTORY DATA ****//*


            if(!visitCollectionId.isEmpty() && !visitCollectionId.equals("null") && visitCollectionId != null)
            {
                visitCollObj = new UpdateDataService(VISIT_COLLECTION_HISTORY_SERVICE);

                visitCollObj.CallServiceObject(VISIT_COLLECTION_HISTORY_ACTION_1, SAVE_NEW_VISIT_COLLECTION_HISTORY, VISIT_COLLECTION_HISTORY_OBJ,
                        "bus:Amount", cashAmount,
                        "bus:Bankaddress", bankAddress,
                        "bus:Bankname", bankName,
                        "bus:Bid", bId,
                        "bus:CenterOfPoliceStation", seizePoliceStationCenter,
                        "bus:ChasisNo", seizeChasisNo,
                        "bus:Chequeno", chequeNo,
                        "bus:Cid", cId,
                        "bus:Coapp1Exist", coApp1Exist,
                        "bus:Coapp1IdProof", coApp1IDProof,
                        "bus:Coapp1IdProofAadharCard", coApp1IDProofAdharCard,
                        "bus:Coapp1IdProofDrivingLicense", coApp1IDProofDrivingLicense,
                        "bus:Coapp1IdProofElectionCard", coApp1IDProofElectionCard,
                        "bus:Coapp1IdProofOther", coApp1IDProofOther,
                        "bus:Coapp1IdProofOtherName", coApp1IDProofOtherName,
                        "bus:Coapp1IdProofPanCard", coApp1IDProofPanCard,
                        "bus:Coapp1Photo", coApp1Photo,
                        "bus:Coapp1ResiProof", coApp1ResiProof,
                        "bus:Coapp1ResiProofAadharCard", coApp1ResiProofAdharCard,
                        "bus:Coapp1ResiProofLightBill", coApp1ResiProofLightBill,
                        "bus:Coapp1ResiProofOther", coApp1ResiProofOther,
                        "bus:Coapp1ResiProofOtherName", coApp1ResiProofOtherName,
                        "bus:Coapp1ResiProofTaxBill", coApp1ResiProofTaxBill,
                        "bus:Coapp1SecurityCheque", coApp1SecurityCheque,
                        "bus:Coapp2Exist", coApp2Exist,
                        "bus:Coapp2IdProof", coApp2IDProof,
                        "bus:Coapp2IdProofAadharCard", coApp2IDProofAdharCard,
                        "bus:Coapp2IdProofDrivingLicense", coApp2IDProofDrivingLicense,
                        "bus:Coapp2IdProofElectionCard", coApp2IDProofElectionCard,
                        "bus:Coapp2IdProofOther", coApp2IDProofOther,
                        "bus:Coapp2IdProofOtherName", coApp2IDProofOtherName,
                        "bus:Coapp2IdProofPanCard", coApp2IDProofPanCard,
                        "bus:Coapp2Photo", coApp2Photo,
                        "bus:Coapp2ResiProof", coApp2ResiProof,
                        "bus:Coapp2ResiProofAadharCard", coApp2ResiProofAdharCard,
                        "bus:Coapp2ResiProofLightBill", coApp2ResiProofLightBill,
                        "bus:Coapp2ResiProofOther", coApp2ResiProofOther,
                        "bus:Coapp2ResiProofTaxBill", coApp2ResiProofTaxBill,
                        "bus:Coapp2ResiRpoofOtherName", coApp2ResiProofOtherName,
                        "bus:Coapp2SecurityCheque", coApp2SecurityCheque,
                        "bus:CollectionLatitude", collectionLatitude,
                        "bus:CollectionLongitude", collectionLongitude,
                        "bus:CustomerMobile", customerMobile,
                        "bus:Ddno", ddNo,
                        "bus:DownPaymentAmt", downPaymentAmount,
                        "bus:EmailPoliceStation", seizePoliceStationEmail,
                        "bus:Emi", calcEmi,
                        "bus:EngineNo", seizeEngineNo,
                        "bus:Entrydate", entryDate,
                        "bus:FaxNoPoliceStation", seizePoliceStationFax,
                        "bus:FileApprovalAmt", fileApprovalAmount,
                        "bus:FileInstAmt", fileInstallmentAmount,
                        "bus:FileInstNo", fileInstallmentNo,
                        "bus:FileLoanAmt", fileLoanAmount,
                        "bus:Ifsccode", ifscCode,
                        "bus:Isactive", isActive,
                        "bus:Isdelete", isDelete,
                        "bus:Issync", isSync,
                        "bus:LoanAmt", calcLoanAmount,
                        "bus:LoaneeExist", loaneeExist,
                        "bus:LoaneeIdProof", loaneeIDProof,
                        "bus:LoaneeIdProofAadharCard", loaneeIDProofAdharCard,
                        "bus:LoaneeIdProofDrivingLicense", loaneeIDProofDrivingLicense,
                        "bus:LoaneeIdProofElectionCard", loaneeIDProofElectionCard,
                        "bus:LoaneeIdProofOther", loaneeIDProofOther,
                        "bus:LoaneeIdProofOtherName", loaneeIDProofOtherName,
                        "bus:LoaneeIdProofPanCard", loaneeIDProofPanCard,
                        "bus:LoaneePhoto", loaneePhoto,
                        "bus:LoaneeResiProof", loaneeResiProof,
                        "bus:LoaneeResiProofAadharCard", loaneeResiProofAdharCard,
                        "bus:LoaneeResiProofLightBill", loaneeResiProofLightBill,
                        "bus:LoaneeResiProofOther", loaneeResiProofOther,
                        "bus:LoaneeResiProofOtherName", loaneeResiProofOtherName,
                        "bus:LoaneeResiProofTaxBill", loaneeResiProofTaxBill,
                        "bus:LoaneeSecurityCheque", loaneeSecurityCheque,
                        "bus:Micrcode", micrCode,
                        "bus:NameOfPoliceStation", seizePoliceStationName,
                        "bus:Notes", notes,
                        "bus:PaymentType", paymentType,
                        "bus:PlanVisitId", planVisitId,
                        "bus:PostInfoToPolice", postInfoPolice,
                        "bus:PriorInfoToPolice", priorInfoPolice,
                        "bus:PtpAmt", ptpModeAmount,
                        "bus:PtpDate", ptpModeDate,
                        "bus:PtpMode", ptpMode,
                        "bus:PtpTime", ptpModeTime,
                        "bus:RateOfInterest", calcRoi,
                        "bus:Remarkid", remarkId,
                        "bus:Rid", visitCollectionId,
                        "bus:SeizingAgencyName", seizingAgencyName,
                        "bus:Sid", sId,
                        "bus:Status", status,
                        "bus:Tenure", calcTenure,
                        "bus:TransactionDate", transactionDate,
                        "bus:VehicleName", seizeVehicleName,
                        "bus:VehicleNo", seizeVehicleNo,
                        "bus:VehiclePhoto1", vehiclePhoto1,
                        "bus:VehiclePhoto2", vehiclePhoto2,
                        "bus:VehiclePhoto3", vehiclePhoto3,
                        "bus:VisitTypeActual", visitTypeActual);
            }


            // Call Method to Display Message and Navigate User
            DisplayMessageAndNavigateToFactSheet(visitId);


            */
/*visitCollObj = new UpdateDataService(VISIT_COLLECTION_SERVICE);

            visitCollObj.CallServiceObject(VISIT_COLLECTION_ACTION_1, VISIT_COLLECTION_COUNT, VISIT_COLLECTION_OBJ, "bus:PlanVisitId", planVisitId, "bus:Isdelete", 0);

            visitCount = Integer.parseInt((String) visitCollObj.getVariableValue("VisitCollectionCollectionFromSearchFieldsCountResult", String.class));

            if(visitCount == 0)
            {
                visitCollObj = new UpdateDataService(VISIT_COLLECTION_SERVICE);

                visitCollObj.CallServiceObject(VISIT_COLLECTION_ACTION_3, SAVE_NEW_VISIT_COLLECTION, VISIT_COLLECTION_OBJ,
                        "bus:Amount", cashAmount,
                        "bus:Bankaddress", bankAddress,
                        "bus:Bankname", bankName,
                        "bus:Bid", bId,
                        "bus:CenterOfPoliceStation", seizePoliceStationCenter,
                        "bus:ChasisNo", seizeChasisNo,
                        "bus:Chequeno", chequeNo,
                        "bus:Cid", cId,
                        "bus:Coapp1Exist", coApp1Exist,
                        "bus:Coapp1IdProof", coApp1IDProof,
                        "bus:Coapp1IdProofAadharCard", coApp1IDProofAdharCard,
                        "bus:Coapp1IdProofDrivingLicense", coApp1IDProofDrivingLicense,
                        "bus:Coapp1IdProofElectionCard", coApp1IDProofElectionCard,
                        "bus:Coapp1IdProofOther", coApp1IDProofOther,
                        "bus:Coapp1IdProofOtherName", coApp1IDProofOtherName,
                        "bus:Coapp1IdProofPanCard", coApp1IDProofPanCard,
                        "bus:Coapp1Photo", coApp1Photo,
                        "bus:Coapp1ResiProof", coApp1ResiProof,
                        "bus:Coapp1ResiProofAadharCard", coApp1ResiProofAdharCard,
                        "bus:Coapp1ResiProofLightBill", coApp1ResiProofLightBill,
                        "bus:Coapp1ResiProofOther", coApp1ResiProofOther,
                        "bus:Coapp1ResiProofOtherName", coApp1ResiProofOtherName,
                        "bus:Coapp1ResiProofTaxBill", coApp1ResiProofTaxBill,
                        "bus:Coapp1SecurityCheque", coApp1SecurityCheque,
                        "bus:Coapp2Exist", coApp2Exist,
                        "bus:Coapp2IdProof", coApp2IDProof,
                        "bus:Coapp2IdProofAadharCard", coApp2IDProofAdharCard,
                        "bus:Coapp2IdProofDrivingLicense", coApp2IDProofDrivingLicense,
                        "bus:Coapp2IdProofElectionCard", coApp2IDProofElectionCard,
                        "bus:Coapp2IdProofOther", coApp2IDProofOther,
                        "bus:Coapp2IdProofOtherName", coApp2IDProofOtherName,
                        "bus:Coapp2IdProofPanCard", coApp2IDProofPanCard,
                        "bus:Coapp2Photo", coApp2Photo,
                        "bus:Coapp2ResiProof", coApp2ResiProof,
                        "bus:Coapp2ResiProofAadharCard", coApp2ResiProofAdharCard,
                        "bus:Coapp2ResiProofLightBill", coApp2ResiProofLightBill,
                        "bus:Coapp2ResiProofOther", coApp2ResiProofOther,
                        "bus:Coapp2ResiProofTaxBill", coApp2ResiProofTaxBill,
                        "bus:Coapp2ResiRpoofOtherName", coApp2ResiProofOtherName,
                        "bus:Coapp2SecurityCheque", coApp2SecurityCheque,
                        "bus:CollectionLatitude", collectionLatitude,
                        "bus:CollectionLongitude", collectionLongitude,
                        "bus:CustomerMobile", customerMobile,
                        "bus:Ddno", ddNo,
                        "bus:DownPaymentAmt", downPaymentAmount,
                        "bus:EmailPoliceStation", seizePoliceStationEmail,
                        "bus:Emi", calcEmi,
                        "bus:EngineNo", seizeEngineNo,
                        "bus:Entrydate", entryDate,
                        "bus:FaxNoPoliceStation", seizePoliceStationFax,
                        "bus:FileApprovalAmt", fileApprovalAmount,
                        "bus:FileInstAmt", fileInstallmentAmount,
                        "bus:FileInstNo", fileInstallmentNo,
                        "bus:FileLoanAmt", fileLoanAmount,
                        "bus:Ifsccode", ifscCode,
                        "bus:Isactive", isActive,
                        "bus:Isdelete", isDelete,
                        "bus:Issync", isSync,
                        "bus:LoanAmt", calcLoanAmount,
                        "bus:LoaneeExist", loaneeExist,
                        "bus:LoaneeIdProof", loaneeIDProof,
                        "bus:LoaneeIdProofAadharCard", loaneeIDProofAdharCard,
                        "bus:LoaneeIdProofDrivingLicense", loaneeIDProofDrivingLicense,
                        "bus:LoaneeIdProofElectionCard", loaneeIDProofElectionCard,
                        "bus:LoaneeIdProofOther", loaneeIDProofOther,
                        "bus:LoaneeIdProofOtherName", loaneeIDProofOtherName,
                        "bus:LoaneeIdProofPanCard", loaneeIDProofPanCard,
                        "bus:LoaneePhoto", loaneePhoto,
                        "bus:LoaneeResiProof", loaneeResiProof,
                        "bus:LoaneeResiProofAadharCard", loaneeResiProofAdharCard,
                        "bus:LoaneeResiProofLightBill", loaneeResiProofLightBill,
                        "bus:LoaneeResiProofOther", loaneeResiProofOther,
                        "bus:LoaneeResiProofOtherName", loaneeResiProofOtherName,
                        "bus:LoaneeResiProofTaxBill", loaneeResiProofTaxBill,
                        "bus:LoaneeSecurityCheque", loaneeSecurityCheque,
                        "bus:Micrcode", micrCode,
                        "bus:NameOfPoliceStation", seizePoliceStationName,
                        "bus:Notes", notes,
                        "bus:PaymentType", paymentType,
                        "bus:PlanVisitId", planVisitId,
                        "bus:PostInfoToPolice", postInfoPolice,
                        "bus:PriorInfoToPolice", priorInfoPolice,
                        "bus:PtpAmt", ptpModeAmount,
                        "bus:PtpDate", ptpModeDate,
                        "bus:PtpMode", ptpMode,
                        "bus:PtpTime", ptpModeTime,
                        "bus:RateOfInterest", calcRoi,
                        "bus:Remarkid", remarkId,
                        "bus:SeizingAgencyName", seizingAgencyName,
                        "bus:Sid", sId,
                        "bus:Status", status,
                        "bus:Tenure", calcTenure,
                        "bus:TransactionDate", transactionDate,
                        "bus:VehicleName", seizeVehicleName,
                        "bus:VehicleNo", seizeVehicleNo,
                        "bus:VehiclePhoto1", vehiclePhoto1,
                        "bus:VehiclePhoto2", vehiclePhoto2,
                        "bus:VehiclePhoto3", vehiclePhoto3,
                        "bus:VisitTypeActual", visitTypeActual);
            }
            else
            {
                visitCollObj = new UpdateDataService(VISIT_COLLECTION_SERVICE);

                visitCollObj.CallServiceObject(VISIT_COLLECTION_ACTION_2, VISIT_COLLECTION_DETAILS, VISIT_COLLECTION_OBJ, "bus:PlanVisitId", planVisitId);

                visitCollectionId = (String) visitCollObj.getVariableValue("a:Id", String.class);

                visitCollObj.CallServiceObject(VISIT_COLLECTION_ACTION_4, UPDATE_VISIT_COLLECTION, VISIT_COLLECTION_OBJ,
                        "bus:Amount", cashAmount,
                        "bus:Bankaddress", bankAddress,
                        "bus:Bankname", bankName,
                        "bus:Bid", bId,
                        "bus:CenterOfPoliceStation", seizePoliceStationCenter,
                        "bus:ChasisNo", seizeChasisNo,
                        "bus:Chequeno", chequeNo,
                        "bus:Cid", cId,
                        "bus:Coapp1Exist", coApp1Exist,
                        "bus:Coapp1IdProof", coApp1IDProof,
                        "bus:Coapp1IdProofAadharCard", coApp1IDProofAdharCard,
                        "bus:Coapp1IdProofDrivingLicense", coApp1IDProofDrivingLicense,
                        "bus:Coapp1IdProofElectionCard", coApp1IDProofElectionCard,
                        "bus:Coapp1IdProofOther", coApp1IDProofOther,
                        "bus:Coapp1IdProofOtherName", coApp1IDProofOtherName,
                        "bus:Coapp1IdProofPanCard", coApp1IDProofPanCard,
                        "bus:Coapp1Photo", coApp1Photo,
                        "bus:Coapp1ResiProof", coApp1ResiProof,
                        "bus:Coapp1ResiProofAadharCard", coApp1ResiProofAdharCard,
                        "bus:Coapp1ResiProofLightBill", coApp1ResiProofLightBill,
                        "bus:Coapp1ResiProofOther", coApp1ResiProofOther,
                        "bus:Coapp1ResiProofOtherName", coApp1ResiProofOtherName,
                        "bus:Coapp1ResiProofTaxBill", coApp1ResiProofTaxBill,
                        "bus:Coapp1SecurityCheque", coApp1SecurityCheque,
                        "bus:Coapp2Exist", coApp2Exist,
                        "bus:Coapp2IdProof", coApp2IDProof,
                        "bus:Coapp2IdProofAadharCard", coApp2IDProofAdharCard,
                        "bus:Coapp2IdProofDrivingLicense", coApp2IDProofDrivingLicense,
                        "bus:Coapp2IdProofElectionCard", coApp2IDProofElectionCard,
                        "bus:Coapp2IdProofOther", coApp2IDProofOther,
                        "bus:Coapp2IdProofOtherName", coApp2IDProofOtherName,
                        "bus:Coapp2IdProofPanCard", coApp2IDProofPanCard,
                        "bus:Coapp2Photo", coApp2Photo,
                        "bus:Coapp2ResiProof", coApp2ResiProof,
                        "bus:Coapp2ResiProofAadharCard", coApp2ResiProofAdharCard,
                        "bus:Coapp2ResiProofLightBill", coApp2ResiProofLightBill,
                        "bus:Coapp2ResiProofOther", coApp2ResiProofOther,
                        "bus:Coapp2ResiProofTaxBill", coApp2ResiProofTaxBill,
                        "bus:Coapp2ResiRpoofOtherName", coApp2ResiProofOtherName,
                        "bus:Coapp2SecurityCheque", coApp2SecurityCheque,
                        "bus:CollectionLatitude", collectionLatitude,
                        "bus:CollectionLongitude", collectionLongitude,
                        "bus:CustomerMobile", customerMobile,
                        "bus:Ddno", ddNo,
                        "bus:DownPaymentAmt", downPaymentAmount,
                        "bus:EmailPoliceStation", seizePoliceStationEmail,
                        "bus:Emi", calcEmi,
                        "bus:EngineNo", seizeEngineNo,
                        "bus:Entrydate", entryDate,
                        "bus:FaxNoPoliceStation", seizePoliceStationFax,
                        "bus:FileApprovalAmt", fileApprovalAmount,
                        "bus:FileInstAmt", fileInstallmentAmount,
                        "bus:FileInstNo", fileInstallmentNo,
                        "bus:FileLoanAmt", fileLoanAmount,
                        "bus:Id", visitCollectionId,
                        "bus:Ifsccode", ifscCode,
                        "bus:Isactive", isActive,
                        "bus:Isdelete", isDelete,
                        "bus:Issync", isSync,
                        "bus:LoanAmt", calcLoanAmount,
                        "bus:LoaneeExist", loaneeExist,
                        "bus:LoaneeIdProof", loaneeIDProof,
                        "bus:LoaneeIdProofAadharCard", loaneeIDProofAdharCard,
                        "bus:LoaneeIdProofDrivingLicense", loaneeIDProofDrivingLicense,
                        "bus:LoaneeIdProofElectionCard", loaneeIDProofElectionCard,
                        "bus:LoaneeIdProofOther", loaneeIDProofOther,
                        "bus:LoaneeIdProofOtherName", loaneeIDProofOtherName,
                        "bus:LoaneeIdProofPanCard", loaneeIDProofPanCard,
                        "bus:LoaneePhoto", loaneePhoto,
                        "bus:LoaneeResiProof", loaneeResiProof,
                        "bus:LoaneeResiProofAadharCard", loaneeResiProofAdharCard,
                        "bus:LoaneeResiProofLightBill", loaneeResiProofLightBill,
                        "bus:LoaneeResiProofOther", loaneeResiProofOther,
                        "bus:LoaneeResiProofOtherName", loaneeResiProofOtherName,
                        "bus:LoaneeResiProofTaxBill", loaneeResiProofTaxBill,
                        "bus:LoaneeSecurityCheque", loaneeSecurityCheque,
                        "bus:Micrcode", micrCode,
                        "bus:NameOfPoliceStation", seizePoliceStationName,
                        "bus:Notes", notes,
                        "bus:PaymentType", paymentType,
                        "bus:PlanVisitId", planVisitId,
                        "bus:PostInfoToPolice", postInfoPolice,
                        "bus:PriorInfoToPolice", priorInfoPolice,
                        "bus:PtpAmt", ptpModeAmount,
                        "bus:PtpDate", ptpModeDate,
                        "bus:PtpMode", ptpMode,
                        "bus:PtpTime", ptpModeTime,
                        "bus:RateOfInterest", calcRoi,
                        "bus:Remarkid", remarkId,
                        "bus:SeizingAgencyName", seizingAgencyName,
                        "bus:Sid", sId,
                        "bus:Status", status,
                        "bus:Tenure", calcTenure,
                        "bus:TransactionDate", transactionDate,
                        "bus:VehicleName", seizeVehicleName,
                        "bus:VehicleNo", seizeVehicleNo,
                        "bus:VehiclePhoto1", vehiclePhoto1,
                        "bus:VehiclePhoto2", vehiclePhoto2,
                        "bus:VehiclePhoto3", vehiclePhoto3,
                        "bus:VisitTypeActual", visitTypeActual);
            }

            // Call Method to Display Message and Navigate User
            DisplayMessageAndNavigateToFactSheet(visitId); *//*

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/** METHOD TO SAVE & UPDATE VISIT_COLLECTION DATA ( FOR OFFLINE ACCESS ) **//*


    public void SaveAndUpdateVisitCollectionDataOFFLINE(final int visitId)
    {
        try
        {
            switch (visitId)
            {
                case 2:

                    EmptyText_Except_PTP_Mode();

                    break;

                case 3:

                    EmptyText_Except_SEIZE_Mode();

                    break;

                case 4:

                    EmptyText_Except_FILE_Mode();

                    break;

                case 5:

                    EmptyText_Except_REMARK_Mode();

                    break;
            }


            // Insert Record in VISIT_COLLECTION Table

            visitCollectionId = String.valueOf(dbAdapter.InsertVisitCollectionData(planVisitId, visitTypeActual, customerMobile, collectionLatitude, collectionLongitude, paymentType, cashAmount, chequeNo, bankName, ddNo, micrCode, ifscCode, bankAddress, transactionDate, ptpMode,
                    ptpModeDate, ptpModeTime, ptpModeAmount, fileApprovalAmount, downPaymentAmount, fileLoanAmount, fileInstallmentNo, fileInstallmentAmount, calcLoanAmount, calcEmi, calcTenure, calcRoi, seizeVehicleName, seizeVehicleNo,
                    seizeEngineNo, seizeChasisNo, seizePoliceStationName, seizePoliceStationCenter, seizingAgencyName, seizePoliceStationFax, seizePoliceStationEmail, loaneeExist, loaneePhoto, loaneeResiProof, loaneeIDProof, loaneeSecurityCheque,
                    loaneeResiProofLightBill, loaneeResiProofTaxBill, loaneeResiProofAdharCard, loaneeResiProofOther, loaneeResiProofOtherName, loaneeIDProofPanCard, loaneeIDProofElectionCard, loaneeIDProofAdharCard, loaneeIDProofDrivingLicense,
                    loaneeIDProofOther, loaneeIDProofOtherName, coApp1Exist, coApp1Photo, coApp1ResiProof, coApp1IDProof, coApp1SecurityCheque, coApp1ResiProofLightBill, coApp1ResiProofTaxBill, coApp1ResiProofAdharCard, coApp1ResiProofOther,
                    coApp1ResiProofOtherName, coApp1IDProofPanCard, coApp1IDProofElectionCard, coApp1IDProofAdharCard, coApp1IDProofDrivingLicense, coApp1IDProofOther, coApp1IDProofOtherName, coApp2Exist, coApp2Photo, coApp2ResiProof, coApp2IDProof,
                    coApp2SecurityCheque, coApp2ResiProofLightBill, coApp2ResiProofTaxBill, coApp2ResiProofAdharCard, coApp2ResiProofOther, coApp2ResiProofOtherName, coApp2IDProofPanCard, coApp2IDProofElectionCard, coApp2IDProofAdharCard,
                    coApp2IDProofDrivingLicense, coApp2IDProofOther, coApp2IDProofOtherName, priorInfoPolice, postInfoPolice, vehiclePhoto1, vehiclePhoto2, vehiclePhoto3, remarkId, bId, cId, isActive, isDelete, entryDate, status, isSync, sId, notes));


            // Insert Record in VISIT_COLLECTION_HISTORY Table

            if(!visitCollectionId.isEmpty() && !visitCollectionId.equals("null") && visitCollectionId != null)
            {
                dbAdapter.InsertVisitCollectionHistoryData(visitCollectionId,planVisitId, visitTypeActual, customerMobile, collectionLatitude, collectionLongitude, paymentType, cashAmount, chequeNo, bankName, ddNo, micrCode, ifscCode, bankAddress, transactionDate, ptpMode,
                        ptpModeDate, ptpModeTime, ptpModeAmount, fileApprovalAmount, downPaymentAmount, fileLoanAmount, fileInstallmentNo, fileInstallmentAmount, calcLoanAmount, calcEmi, calcTenure, calcRoi, seizeVehicleName, seizeVehicleNo,
                        seizeEngineNo, seizeChasisNo, seizePoliceStationName, seizePoliceStationCenter, seizingAgencyName, seizePoliceStationFax, seizePoliceStationEmail, loaneeExist, loaneePhoto, loaneeResiProof, loaneeIDProof, loaneeSecurityCheque,
                        loaneeResiProofLightBill, loaneeResiProofTaxBill, loaneeResiProofAdharCard, loaneeResiProofOther, loaneeResiProofOtherName, loaneeIDProofPanCard, loaneeIDProofElectionCard, loaneeIDProofAdharCard, loaneeIDProofDrivingLicense,
                        loaneeIDProofOther, loaneeIDProofOtherName, coApp1Exist, coApp1Photo, coApp1ResiProof, coApp1IDProof, coApp1SecurityCheque, coApp1ResiProofLightBill, coApp1ResiProofTaxBill, coApp1ResiProofAdharCard, coApp1ResiProofOther,
                        coApp1ResiProofOtherName, coApp1IDProofPanCard, coApp1IDProofElectionCard, coApp1IDProofAdharCard, coApp1IDProofDrivingLicense, coApp1IDProofOther, coApp1IDProofOtherName, coApp2Exist, coApp2Photo, coApp2ResiProof, coApp2IDProof,
                        coApp2SecurityCheque, coApp2ResiProofLightBill, coApp2ResiProofTaxBill, coApp2ResiProofAdharCard, coApp2ResiProofOther, coApp2ResiProofOtherName, coApp2IDProofPanCard, coApp2IDProofElectionCard, coApp2IDProofAdharCard,
                        coApp2IDProofDrivingLicense, coApp2IDProofOther, coApp2IDProofOtherName, priorInfoPolice, postInfoPolice, vehiclePhoto1, vehiclePhoto2, vehiclePhoto3, remarkId, bId, cId, isActive, isDelete, entryDate, status, isSync, sId, notes);
            }
            */
/*Cursor c1 = dbAdapter.CheckVisitCollectionData(planVisitId);

            if(c1 != null)
            {
                visitCount = c1.getCount();

                if(visitCount == 0)
                {
                    dbAdapter.InsertVisitCollectionData(planVisitId, visitTypeActual, customerMobile, collectionLatitude, collectionLongitude, paymentType, cashAmount, chequeNo, bankName, ddNo, micrCode, ifscCode, bankAddress, transactionDate, ptpMode,
                            ptpModeDate, ptpModeTime, ptpModeAmount, fileApprovalAmount, downPaymentAmount, fileLoanAmount, fileInstallmentNo, fileInstallmentAmount, calcLoanAmount, calcEmi, calcTenure, calcRoi, seizeVehicleName, seizeVehicleNo,
                            seizeEngineNo, seizeChasisNo, seizePoliceStationName, seizePoliceStationCenter, seizingAgencyName, seizePoliceStationFax, seizePoliceStationEmail, loaneeExist, loaneePhoto, loaneeResiProof, loaneeIDProof, loaneeSecurityCheque,
                            loaneeResiProofLightBill, loaneeResiProofTaxBill, loaneeResiProofAdharCard, loaneeResiProofOther, loaneeResiProofOtherName, loaneeIDProofPanCard, loaneeIDProofElectionCard, loaneeIDProofAdharCard, loaneeIDProofDrivingLicense,
                            loaneeIDProofOther, loaneeIDProofOtherName, coApp1Exist, coApp1Photo, coApp1ResiProof, coApp1IDProof, coApp1SecurityCheque, coApp1ResiProofLightBill, coApp1ResiProofTaxBill, coApp1ResiProofAdharCard, coApp1ResiProofOther,
                            coApp1ResiProofOtherName, coApp1IDProofPanCard, coApp1IDProofElectionCard, coApp1IDProofAdharCard, coApp1IDProofDrivingLicense, coApp1IDProofOther, coApp1IDProofOtherName, coApp2Exist, coApp2Photo, coApp2ResiProof, coApp2IDProof,
                            coApp2SecurityCheque, coApp2ResiProofLightBill, coApp2ResiProofTaxBill, coApp2ResiProofAdharCard, coApp2ResiProofOther, coApp2ResiProofOtherName, coApp2IDProofPanCard, coApp2IDProofElectionCard, coApp2IDProofAdharCard,
                            coApp2IDProofDrivingLicense, coApp2IDProofOther, coApp2IDProofOtherName, priorInfoPolice, postInfoPolice, vehiclePhoto1, vehiclePhoto2, vehiclePhoto3, remarkId, bId, cId, isActive, isDelete, entryDate, status, isSync, sId, notes);
                }
                else
                {
                    dbAdapter.UpdateVisitCollectionData(planVisitId, visitTypeActual, customerMobile, collectionLatitude, collectionLongitude, paymentType, cashAmount, chequeNo, bankName, ddNo, micrCode, ifscCode, bankAddress, transactionDate, ptpMode,
                            ptpModeDate, ptpModeTime, ptpModeAmount, fileApprovalAmount, downPaymentAmount, fileLoanAmount, fileInstallmentNo, fileInstallmentAmount, calcLoanAmount, calcEmi, calcTenure, calcRoi, seizeVehicleName, seizeVehicleNo,
                            seizeEngineNo, seizeChasisNo, seizePoliceStationName, seizePoliceStationCenter, seizingAgencyName, seizePoliceStationFax, seizePoliceStationEmail, loaneeExist, loaneePhoto, loaneeResiProof, loaneeIDProof, loaneeSecurityCheque,
                            loaneeResiProofLightBill, loaneeResiProofTaxBill, loaneeResiProofAdharCard, loaneeResiProofOther, loaneeResiProofOtherName, loaneeIDProofPanCard, loaneeIDProofElectionCard, loaneeIDProofAdharCard, loaneeIDProofDrivingLicense,
                            loaneeIDProofOther, loaneeIDProofOtherName, coApp1Exist, coApp1Photo, coApp1ResiProof, coApp1IDProof, coApp1SecurityCheque, coApp1ResiProofLightBill, coApp1ResiProofTaxBill, coApp1ResiProofAdharCard, coApp1ResiProofOther,
                            coApp1ResiProofOtherName, coApp1IDProofPanCard, coApp1IDProofElectionCard, coApp1IDProofAdharCard, coApp1IDProofDrivingLicense, coApp1IDProofOther, coApp1IDProofOtherName, coApp2Exist, coApp2Photo, coApp2ResiProof, coApp2IDProof,
                            coApp2SecurityCheque, coApp2ResiProofLightBill, coApp2ResiProofTaxBill, coApp2ResiProofAdharCard, coApp2ResiProofOther, coApp2ResiProofOtherName, coApp2IDProofPanCard, coApp2IDProofElectionCard, coApp2IDProofAdharCard,
                            coApp2IDProofDrivingLicense, coApp2IDProofOther, coApp2IDProofOtherName, priorInfoPolice, postInfoPolice, vehiclePhoto1, vehiclePhoto2, vehiclePhoto3, remarkId, bId, cId, isActive, isDelete, entryDate, status, isSync, sId, notes);
                }
            }*//*



            // Call Method to Display Message and Navigate User
            DisplayMessageAndNavigateToFactSheet(visitId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/** METHOD TO DISPLAY MESSAGE & NAVIGATE USER TO FACT_SHEET ACTIVITY **//*


    public void DisplayMessageAndNavigateToFactSheet(final int visitId)
    {
        try
        {
            runOnUiThread(new Runnable()
            {
                public void run()
                {
                    // Display Message Based on Field Visit Mode
                    switch (visitId)
                    {
                        case 2:

                            DialogConfig.getInstance().DisplayCustomToastMsg(FieldVisitCustomerDetailsPlanned.this, Config.PTP_SUCCESS);
                            dialogPtpMode.dismiss();
                            break;

                        case 3:

                            DialogConfig.getInstance().DisplayCustomToastMsg(FieldVisitCustomerDetailsPlanned.this, Config.SEIZE_SUCCESS);
                            dialogSeizeMode.dismiss();
                            break;

                        case 4:
                            DialogConfig.getInstance().DisplayCustomToastMsg(FieldVisitCustomerDetailsPlanned.this, Config.FILE_SUCCESS);
                            dialogFileMode.dismiss();
                            break;

                        case 5:

                            DialogConfig.getInstance().DisplayCustomToastMsg(FieldVisitCustomerDetailsPlanned.this, Config.REMARK_SUCCESS);
                            dialogRemarkMode.dismiss();
                            break;
                    }

                    // Call Method to Navigate User to FACT_SHEET Avtivity
                    OpenFactSheetActivity();
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    */
/** Set Default Values Except Variables Used for PTP MODE **//*


    public void EmptyText_Except_PTP_Mode()
    {
        coApp1Exist = "";
        coApp1IDProof = "";
        coApp1IDProofAdharCard = "";
        coApp1IDProofDrivingLicense = "";
        coApp1IDProofElectionCard = "";
        coApp1IDProofOther = "";
        coApp1IDProofOtherName = "";
        coApp1IDProofPanCard = "";
        coApp1Photo = "";
        coApp1ResiProof = "";
        coApp1ResiProofAdharCard = "";
        coApp1ResiProofLightBill = "";
        coApp1ResiProofOther = "";
        coApp1ResiProofOtherName = "";
        coApp1ResiProofTaxBill = "";
        coApp1SecurityCheque = "";

        coApp2Exist = "";
        coApp2IDProof = "";
        coApp2IDProofAdharCard = "";
        coApp2IDProofDrivingLicense = "";
        coApp2IDProofElectionCard = "";
        coApp2IDProofOther = "";
        coApp2IDProofOtherName = "";
        coApp2IDProofPanCard = "";
        coApp2Photo = "";
        coApp2ResiProof = "";
        coApp2ResiProofAdharCard = "";
        coApp2ResiProofLightBill = "";
        coApp2ResiProofOther = "";
        coApp2ResiProofOtherName = "";
        coApp2ResiProofTaxBill = "";
        coApp2SecurityCheque = "";

        collectionLatitude = "";
        collectionLongitude = "";

        loaneeExist = "";
        loaneeIDProof = "";
        loaneeIDProofAdharCard = "";
        loaneeIDProofDrivingLicense = "";
        loaneeIDProofElectionCard = "";
        loaneeIDProofOther = "";
        loaneeIDProofOtherName = "";
        loaneeIDProofPanCard = "";
        loaneePhoto = "";
        loaneeResiProof = "";
        loaneeResiProofAdharCard = "";
        loaneeResiProofLightBill = "";
        loaneeResiProofOther = "";
        loaneeResiProofOtherName = "";
        loaneeResiProofTaxBill = "";
        loaneeSecurityCheque = "";

        notes = "";
        postInfoPolice = "";
        priorInfoPolice = "";
        remarkId = "";
        vehiclePhoto1 = "";
        vehiclePhoto2 = "";
        vehiclePhoto3 = "";
    }


    */
/** Set Default Values Except Variables Used for FILE MODE **//*


    public void EmptyText_Except_FILE_Mode()
    {
        collectionLatitude = "";
        collectionLongitude = "";

        notes = "";
        postInfoPolice = "";
        priorInfoPolice = "";
        remarkId = "";
        vehiclePhoto1 = "";
        vehiclePhoto2 = "";
        vehiclePhoto3 = "";

        ptpMode = "";
        ptpModeDate = "";
        ptpModeTime = "";
        ptpModeAmount = "";

        seizeVehicleName = "";
        seizeVehicleNo = "";
        seizeEngineNo = "";
        seizeChasisNo = "";
        seizePoliceStationName = "";
        seizePoliceStationCenter = "";
        seizingAgencyName = "";
        seizePoliceStationFax = "";
        seizePoliceStationEmail = "";
    }


    */
/** Set Default Values Except Variables Used for SEIZE MODE **//*


    public void EmptyText_Except_SEIZE_Mode()
    {
        coApp1Exist = "";
        coApp1IDProof = "";
        coApp1IDProofAdharCard = "";
        coApp1IDProofDrivingLicense = "";
        coApp1IDProofElectionCard = "";
        coApp1IDProofOther = "";
        coApp1IDProofOtherName = "";
        coApp1IDProofPanCard = "";
        coApp1Photo = "";
        coApp1ResiProof = "";
        coApp1ResiProofAdharCard = "";
        coApp1ResiProofLightBill = "";
        coApp1ResiProofOther = "";
        coApp1ResiProofOtherName = "";
        coApp1ResiProofTaxBill = "";
        coApp1SecurityCheque = "";

        coApp2Exist = "";
        coApp2IDProof = "";
        coApp2IDProofAdharCard = "";
        coApp2IDProofDrivingLicense = "";
        coApp2IDProofElectionCard = "";
        coApp2IDProofOther = "";
        coApp2IDProofOtherName = "";
        coApp2IDProofPanCard = "";
        coApp2Photo = "";
        coApp2ResiProof = "";
        coApp2ResiProofAdharCard = "";
        coApp2ResiProofLightBill = "";
        coApp2ResiProofOther = "";
        coApp2ResiProofOtherName = "";
        coApp2ResiProofTaxBill = "";
        coApp2SecurityCheque = "";

        collectionLatitude = "";
        collectionLongitude = "";

        loaneeExist = "";
        loaneeIDProof = "";
        loaneeIDProofAdharCard = "";
        loaneeIDProofDrivingLicense = "";
        loaneeIDProofElectionCard = "";
        loaneeIDProofOther = "";
        loaneeIDProofOtherName = "";
        loaneeIDProofPanCard = "";
        loaneePhoto = "";
        loaneeResiProof = "";
        loaneeResiProofAdharCard = "";
        loaneeResiProofLightBill = "";
        loaneeResiProofOther = "";
        loaneeResiProofOtherName = "";
        loaneeResiProofTaxBill = "";
        loaneeSecurityCheque = "";

        notes = "";
        remarkId = "";

        ptpMode = "";
        ptpModeDate = "";
        ptpModeTime = "";
        ptpModeAmount = "";
        fileApprovalAmount = "";
        downPaymentAmount = "";
        fileLoanAmount = "";
        fileInstallmentNo = "";
        fileInstallmentAmount = "";
        calcLoanAmount = "";
        calcEmi = "";
        calcRoi = "";
        calcTenure = "";
        seizeVehicleName = "";
        seizeVehicleNo = "";
        seizeEngineNo = "";
        seizeChasisNo = "";
        seizePoliceStationName = "";
        seizePoliceStationCenter = "";
        seizingAgencyName = "";
        seizePoliceStationFax = "";
        seizePoliceStationEmail = "";
    }


    */
/** Set Default Values Except Variables Used for REMARK MODE **//*


    public void EmptyText_Except_REMARK_Mode()
    {
        coApp1Exist = "";
        coApp1IDProof = "";
        coApp1IDProofAdharCard = "";
        coApp1IDProofDrivingLicense = "";
        coApp1IDProofElectionCard = "";
        coApp1IDProofOther = "";
        coApp1IDProofOtherName = "";
        coApp1IDProofPanCard = "";
        coApp1Photo = "";
        coApp1ResiProof = "";
        coApp1ResiProofAdharCard = "";
        coApp1ResiProofLightBill = "";
        coApp1ResiProofOther = "";
        coApp1ResiProofOtherName = "";
        coApp1ResiProofTaxBill = "";
        coApp1SecurityCheque = "";

        coApp2Exist = "";
        coApp2IDProof = "";
        coApp2IDProofAdharCard = "";
        coApp2IDProofDrivingLicense = "";
        coApp2IDProofElectionCard = "";
        coApp2IDProofOther = "";
        coApp2IDProofOtherName = "";
        coApp2IDProofPanCard = "";
        coApp2Photo = "";
        coApp2ResiProof = "";
        coApp2ResiProofAdharCard = "";
        coApp2ResiProofLightBill = "";
        coApp2ResiProofOther = "";
        coApp2ResiProofOtherName = "";
        coApp2ResiProofTaxBill = "";
        coApp2SecurityCheque = "";

        collectionLatitude = "";
        collectionLongitude = "";

        loaneeExist = "";
        loaneeIDProof = "";
        loaneeIDProofAdharCard = "";
        loaneeIDProofDrivingLicense = "";
        loaneeIDProofElectionCard = "";
        loaneeIDProofOther = "";
        loaneeIDProofOtherName = "";
        loaneeIDProofPanCard = "";
        loaneePhoto = "";
        loaneeResiProof = "";
        loaneeResiProofAdharCard = "";
        loaneeResiProofLightBill = "";
        loaneeResiProofOther = "";
        loaneeResiProofOtherName = "";
        loaneeResiProofTaxBill = "";
        loaneeSecurityCheque = "";

        notes = "";
        postInfoPolice = "";
        priorInfoPolice = "";
        vehiclePhoto1 = "";
        vehiclePhoto2 = "";
        vehiclePhoto3 = "";

        ptpMode = "";
        ptpModeDate = "";
        ptpModeTime = "";
        ptpModeAmount = "";
        fileApprovalAmount = "";
        downPaymentAmount = "";
        fileLoanAmount = "";
        fileInstallmentNo = "";
        fileInstallmentAmount = "";
        calcLoanAmount = "";
        calcEmi = "";
        calcRoi = "";
        calcTenure = "";
        seizeVehicleName = "";
        seizeVehicleNo = "";
        seizeEngineNo = "";
        seizeChasisNo = "";
        seizePoliceStationName = "";
        seizePoliceStationCenter = "";
        seizingAgencyName = "";
        seizePoliceStationFax = "";
        seizePoliceStationEmail = "";
    }


    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id)
    {
        switch(id)
        {
            case 0:
                Calendar c1 = Calendar.getInstance();
                int year = c1.get(Calendar.YEAR);
                int month = c1.get(Calendar.MONTH);
                int day = c1.get(Calendar.DAY_OF_MONTH);

                return new DatePickerDialog(this, R.style.CustomCalendar, ptpCashModeDatePicker, year, month, day);

            case 1:
                Calendar c2 = Calendar.getInstance();
                year = c2.get(Calendar.YEAR);
                month = c2.get(Calendar.MONTH);
                day = c2.get(Calendar.DAY_OF_MONTH);

                return new DatePickerDialog(this, R.style.CustomCalendar, ptpFileModeDatePicker, year, month, day);

            case 2:
                Calendar c3 = Calendar.getInstance();
                year = c3.get(Calendar.YEAR);
                month = c3.get(Calendar.MONTH);
                day = c3.get(Calendar.DAY_OF_MONTH);

                return new DatePickerDialog(this, R.style.CustomCalendar, ptpSeizeModeDatePicker, year, month, day);
        }
        return null;
    }


    */
/*** Open Date Picker Dialog for PTP CASH Mode ***//*


    private DatePickerDialog.OnDateSetListener ptpCashModeDatePicker = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay)
        {
            String finalDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            txtCashPtpDate.setText(finalDate);
            setDateOfBirth(selectedYear, selectedMonth, selectedDay);
            getCurrentDate();
            calcualteYear();
            calcualteMonth();
            calcualteDay();
        }

        String getCurrentDate()
        {
            Calendar end = Calendar.getInstance();
            endYear = end.get(Calendar.YEAR);
            endMonth = end.get(Calendar.MONTH);
            endMonth++;
            endDay = end.get(Calendar.DAY_OF_MONTH);
            return endDay + ":" + endMonth + ":" + endYear;
        }

        void setDateOfBirth(int sYear, int sMonth, int sDay)
        {
            startYear = sYear;
            startMonth = sMonth;
            startMonth++;
            startDay = sDay;
        }

        void calcualteYear()
        {
            resYear = endYear - startYear;
        }

        void calcualteMonth()
        {
            if (endMonth >= startMonth)
            {
                resMonth = endMonth - startMonth;
            }
            else
            {
                resMonth = endMonth - startMonth;
                resMonth = 12 + resMonth;
                resYear--;
            }
        }

        void calcualteDay()
        {
            int resDay;
            if (endDay >= startDay)
            {
                resDay = endDay - startDay;
            }
            else
            {
                resDay = endDay - startDay;
                resDay = 30 + resDay;
                if (resMonth == 0)
                {
                    resMonth = 11;
                    resYear--;
                }
                else
                {
                    resMonth--;
                }
            }
        }
    };


    */
/*** Open Date Picker Dialog for PTP FILE Mode ***//*


    private DatePickerDialog.OnDateSetListener ptpFileModeDatePicker = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay)
        {
            String finalDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            txtFilePtpDate.setText(finalDate);
            setDateOfBirth(selectedYear, selectedMonth, selectedDay);
            getCurrentDate();
            calcualteYear();
            calcualteMonth();
            calcualteDay();
        }

        String getCurrentDate()
        {
            Calendar end = Calendar.getInstance();
            endYear = end.get(Calendar.YEAR);
            endMonth = end.get(Calendar.MONTH);
            endMonth++;
            endDay = end.get(Calendar.DAY_OF_MONTH);
            return endDay + ":" + endMonth + ":" + endYear;
        }

        void setDateOfBirth(int sYear, int sMonth, int sDay)
        {
            startYear = sYear;
            startMonth = sMonth;
            startMonth++;
            startDay = sDay;
        }

        void calcualteYear()
        {
            resYear = endYear - startYear;
        }

        void calcualteMonth()
        {
            if (endMonth >= startMonth)
            {
                resMonth = endMonth - startMonth;
            }
            else
            {
                resMonth = endMonth - startMonth;
                resMonth = 12 + resMonth;
                resYear--;
            }
        }

        void calcualteDay()
        {
            int resDay;
            if (endDay >= startDay)
            {
                resDay = endDay - startDay;
            }
            else
            {
                resDay = endDay - startDay;
                resDay = 30 + resDay;
                if (resMonth == 0)
                {
                    resMonth = 11;
                    resYear--;
                }
                else
                {
                    resMonth--;
                }
            }
        }
    };


    */
/*** Open Date Picker Dialog for PTP SEIZE Mode ***//*


    private DatePickerDialog.OnDateSetListener ptpSeizeModeDatePicker = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay)
        {
            String finalDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
            txtSeizePtpDate.setText(finalDate);
            setDateOfBirth(selectedYear, selectedMonth, selectedDay);
            getCurrentDate();
            calcualteYear();
            calcualteMonth();
            calcualteDay();
        }

        String getCurrentDate()
        {
            Calendar end = Calendar.getInstance();
            endYear = end.get(Calendar.YEAR);
            endMonth = end.get(Calendar.MONTH);
            endMonth++;
            endDay = end.get(Calendar.DAY_OF_MONTH);
            return endDay + ":" + endMonth + ":" + endYear;
        }

        void setDateOfBirth(int sYear, int sMonth, int sDay)
        {
            startYear = sYear;
            startMonth = sMonth;
            startMonth++;
            startDay = sDay;
        }

        void calcualteYear()
        {
            resYear = endYear - startYear;
        }

        void calcualteMonth()
        {
            if (endMonth >= startMonth)
            {
                resMonth = endMonth - startMonth;
            }
            else
            {
                resMonth = endMonth - startMonth;
                resMonth = 12 + resMonth;
                resYear--;
            }
        }

        void calcualteDay()
        {
            int resDay;
            if (endDay >= startDay)
            {
                resDay = endDay - startDay;
            }
            else
            {
                resDay = endDay - startDay;
                resDay = 30 + resDay;
                if (resMonth == 0)
                {
                    resMonth = 11;
                    resYear--;
                }
                else
                {
                    resMonth--;
                }
            }
        }
    };


    */
/*** FUCNTION TO EXTRACT XML DATA FROM TAG ***//*


    public static String getCharacterDataFromElement(Element e)
    {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData)
        {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }


    */
/*** FUCNTION TO GET FFEDBACK DATA AS A LIST ***//*


    public List<String> GetFeedBackListData()
    {
        List<String> list1 = new ArrayList<String>();

        String isExist = "true";

        if(feedBackList.size() != 0)
        {
            for(int i = 0; i < feedBackList.size(); i++)
            {
                HashMap<String, String> currentList = feedBackList.get(i);
                String exist = currentList.get("Exist");
                String name = currentList.get("Feedback");

                if(exist.equals(isExist))
                {
                    list1.add(name);
                }
            }
        }

        return list1;
    }


    */
/*** FUCNTION TO CALCULATE EMI FROM LOAN AMOUNT, RATE OF INTEREST AND TENURE ***//*


    public double CalculateEmi(double dLoanAmt, double dInterestRate, double dPeriod)
    {
        double monthlyEmi = 0.0;

        try
        {
            monthlyEmi = ((100 * dLoanAmt) + (dLoanAmt * dInterestRate * dPeriod)) / (100 * dPeriod);

            return monthlyEmi;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return monthlyEmi;
    }

   */
/* public double CalculateEmi(double dLoanAmt, double dInterestRate, double dPeriod)
    {
        double rate = 0.0;
        double monthly = 0.0;

        try
        {
            rate = dInterestRate / 1200;
            monthly = rate > 0 ? ((rate + rate / (Math.pow(1 + rate, dPeriod) - 1)) * dLoanAmt) : dLoanAmt / dPeriod;

            return monthly;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return monthly;
    }*//*



    */
/*** FUCNTION TO CALCULATE LOAN AMOUNT (PRINCIPAL AMOUNT) FROM EMI VALUE, RATE OF INTEREST AND TENURE ***//*


    public double CalculateLoanAmount(double dEmi, double dInterestRate, double dPeriod)
    {
        double principalAmt = 0.0;

        try
        {
            principalAmt = (100 * dPeriod * dEmi) / (100 + (dInterestRate * dPeriod));

            return principalAmt;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return principalAmt;
    }

    */
/*public double CalculateLoanAmount(double dEmi, double dInterestRate, double dPeriod)
    {
        double rate = 0.0;
        double principalAmt = 0.0;

        try
        {
            rate = dInterestRate / 1200;

            //principalAmt = rate > 0 ? ((rate + rate / (Math.pow(1 + rate, dPeriod) - 1)) * dLoanAmt) : dLoanAmt / dPeriod;

            principalAmt = dEmi / (rate * ((Math.pow(1 + rate, dPeriod)) / ((Math.pow(1 + rate, dPeriod)) -1)));

            return principalAmt;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return principalAmt;
    }*//*



    */
/*** FUCNTION TO CALCULATE RATE OF INTEREST (ROI) FROM EMI VALUE, LOAN AMOUNT AND TENURE ***//*


    public double CalculateRateOfIneterest(double dEmi, double dLoanAmt, double dPeriod)
    {
        double interestRate = 0.0;

        try
        {
            interestRate = ((100 * dPeriod * dEmi) - (100 * dLoanAmt)) / (dLoanAmt * dPeriod);

            return interestRate;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return interestRate;
    }


    */
/*** FUCNTION TO CALCULATE TENURE (LOAN PERIOD) FROM EMI VALUE, LOAN AMOUNT AND RATE OF INTEREST (ROI) ***//*


    public double CalculateTenure(double dEmi, double dLoanAmt, double dInterestRate)
    {
        double loanPeriod = 0.0;

        try
        {
            loanPeriod = (100 * dLoanAmt) / ((100 * dEmi) - (dLoanAmt * dInterestRate));

            return loanPeriod;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return loanPeriod;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
*/
