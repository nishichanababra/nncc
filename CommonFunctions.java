package app.citta.retail365cloud.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import app.citta.retail365cloud.R;

public class CommonFunctions {

    public static final String R_BRANCH = "branchform";
    public static final String R_COMPANY = "companyform";
    public static final String R_COMPANY_SETTING = "companysettingform";
    public static final String R_CUSTOMER = "customerform";
    public static final String R_BANK = "bankform";
    public static final String R_EMPLOYEE = "employeeform";
    public static final String R_SUPPLIER = "supplierform";
    public static final String R_EXPENSE = "expenseform";
    public static final String R_EXPENSE_TRANSACTION = "expensetransactionform";
    public static final String R_CATEGORY = "categoryform";
    public static final String R_SUBCATEGORY = "subcategoryform";
    public static final String R_PRODUCT = "productform";
    public static final String R_STOCK = "stockform";
    public static final String R_STOCK_TRANSAFER = "stocktransaferform";
    public static final String R_PURCHASE_ORDER = "purchaseorderform";
    public static final String R_PURCHASE_BILL = "purchasebillform";
    public static final String R_PURCHASE_RETURN = "purchasereturnform";
    public static final String R_SALES = "salesform";
    public static final String R_SALES_RETURN = "salesreturnform";
    public static final String R_QUOTATION = "quotationform";
    public static final String R_GROUP = "groupform";
    public static final String R_PAGE = "pageform";
    public static final String R_BANK_PAYMENT = "bankpaymentform";
    public static final String R_BANK_RECEIPT = "bankreceiptform";
    public static final String R_CASH_PAYMENT = "cashpaymentform";
    public static final String R_CASH_RECEIPT = "cashreceiptform";
    public static final String R_LEDGER_REPORT = "ledgerreport";
    public static final String R_CONTRA_ENTRY = "contraentryform";
    public static final String R_SALES_REPORT = "salesreport";
    public static final String R_SALES_RETURN_REPORT = "salesreturnreport";
    public static final String R_PURCHASE_REPORT = "purchasereport";
    public static final String R_PURCHASE_RETURN_REPORT = "purchasereturnreport";
    public static final String R_CYLINDER_PRODUCT = "productcylinderform";
    public static final String R_CRM_REPORT = "crmreportform";
    public static final String R_MONTH_END_SALE_REPORT = "monthendsalereport";
    public static final String R_PRINT_SETTING = "printsettingform";
    public static final String R_PRINTER_SETTING = "printersettingform";
    public static final String R_CYLINDER_SALES_ORDER = "salescylinderform";
    public static final String R_CYLINDER_SALES_RETURN = "salesreturncylinderform";
    public static final String R_CYLINDER_PURCHASE_RETURN = "purchasereturncylinderform";
    public static final String R_CYLINDER_PURCHASE_BILL = "purchasebillcylinderform";
    public static final String R_GST_REPORT = "gstreport";
    public static final String R_INWARD_STOCK = "inwardstockform";
    public static final String R_STOCK_REPORT = "stockreport";
    public static final String R_EMPTY_CYLINDER_INWARD = "emptycylinderinwardform";
    public static final String R_EMPTY_CYLINDER_OUTWARD = "emptycylinderoutwardform";
    public static final String R_CYLINDER_STOCK_REPORT = "stockcylinderreport";
    public static final String R_CUSTOMER_PAYMENT = "customerpaymentform";
    public static final String R_CYLINDER_OUTSTANDING_REPORT = "cylinderoutstandingreport";
    public static final String R_CYLINDER_OUTSTANDING_SUMMARY = "cylinderoutstandingsummary";
    public static final String R_CYLINDER_SALES_REPORT = "salescylinderreport";
    public static final String R_CYLINDER_SALES_RETURN_REPORT = "salesreturncylinderreport";
    public static final String R_CYLINDER_PURCHASE_REPORT = "purchasecylinderreport";
    public static final String R_CYLINDER_PURCHASE_RETURN_REPORT = "purchasereturncylinderreport";
    public static final String R_BARCODE_PRINTER_SETTING = "printerbarcodesetting";
    public static final String R_BARCODE_GENERATOR = "barcodegenerator";
    public static final String R_VENDOR_PAYMENT = "paymentvendorform";
    public static final String R_SALES_OUTSTANDING_REPORT = "reportsalesoutstandingbillwise";
    public static final String R_SALES_OUTSTANDING_REPORT_SUMMARY = "reportsalesoutstandingsummary";
    public static final String R_SALES_OUTSTANDING_REPORT_GAYA = "reportsalesoutstanding_gaya_billwise";

    /*
     * Method to convert date format from one format to another format
     *
     * */
    @SuppressLint("SimpleDateFormat")
    public static String dateConversion(String inputDateFormat, String outputDateFormat, String inputDate) {

        String outputDate = "";

        try {

            if (!TextUtils.isEmpty(inputDate)) {

                DateFormat originalFormat = new SimpleDateFormat(inputDateFormat, Locale.ENGLISH);
                DateFormat targetFormat = new SimpleDateFormat(outputDateFormat, Locale.ENGLISH);
                Date date = originalFormat.parse(inputDate);
                outputDate = targetFormat.format(date);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return outputDate;
    }

    public static String getRights(Activity activity, String page) {

        String rights = "";

        try {

            rights = AppPreference.getInstance().getStringPreference(activity, activity.getResources().getString(R.string.pref_app_rights));

            if (!TextUtils.isEmpty(rights) && rights.contains(page)) {

                int startpos = rights.indexOf(page);
                int endpos = rights.indexOf(",", rights.indexOf(page));

                if (endpos == -1) {
                    endpos = rights.length();
                }

                switch (page) {

                    case R_BRANCH:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_COMPANY:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_COMPANY_SETTING:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CUSTOMER:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_BANK:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_EMPLOYEE:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_SUPPLIER:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_EXPENSE:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_EXPENSE_TRANSACTION:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CATEGORY:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_SUBCATEGORY:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_PRODUCT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_STOCK:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_STOCK_TRANSAFER:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_PURCHASE_ORDER:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_PURCHASE_BILL:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_PURCHASE_RETURN:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_SALES:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_SALES_RETURN:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_QUOTATION:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_GROUP:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_PAGE:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_BANK_PAYMENT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_BANK_RECEIPT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CASH_PAYMENT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CASH_RECEIPT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_LEDGER_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CONTRA_ENTRY:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_SALES_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_SALES_RETURN_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_PURCHASE_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_PURCHASE_RETURN_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_PRODUCT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CRM_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_MONTH_END_SALE_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_PRINT_SETTING:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_PRINTER_SETTING:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_SALES_ORDER:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_SALES_RETURN:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_PURCHASE_RETURN:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_PURCHASE_BILL:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_GST_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_INWARD_STOCK:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_STOCK_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_EMPTY_CYLINDER_INWARD:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_EMPTY_CYLINDER_OUTWARD:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_STOCK_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CUSTOMER_PAYMENT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_OUTSTANDING_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_OUTSTANDING_SUMMARY:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_SALES_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_SALES_RETURN_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_PURCHASE_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_CYLINDER_PURCHASE_RETURN_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_BARCODE_PRINTER_SETTING:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_BARCODE_GENERATOR:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_VENDOR_PAYMENT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_SALES_OUTSTANDING_REPORT:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_SALES_OUTSTANDING_REPORT_SUMMARY:
                        rights = rights.substring(startpos, endpos);
                        break;

                    case R_SALES_OUTSTANDING_REPORT_GAYA:
                        rights = rights.substring(startpos, endpos);
                        break;
                }
            } else {
                rights = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rights;
    }

    public static void setEditRights(View view, String rights) {

        try {

            if (view != null && !TextUtils.isEmpty(rights)) {

                if (rights.contains("E")) {
                    view.setAlpha(1f);
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setAlpha(0f);
                    view.setVisibility(View.GONE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setDeleteRights(View view, String rights) {

        try {

            if (view != null && !TextUtils.isEmpty(rights)) {

                if (rights.contains("D")) {
                    view.setAlpha(1f);
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setAlpha(0f);
                    view.setVisibility(View.GONE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAddRights(View view, String rights) {

        try {

            if (view != null && !TextUtils.isEmpty(rights)) {

                if (rights.contains("A")) {
                    view.setAlpha(1f);
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setAlpha(0f);
                    view.setVisibility(View.GONE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean haveViewRights(String rights) {

        try {

            if (!TextUtils.isEmpty(rights)) {

                if (rights.contains("V"))
                    return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean haveEditRights(String rights) {

        try {

            if (!TextUtils.isEmpty(rights)) {

                if (rights.contains("E"))
                    return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean haveDeleteRights(String rights) {

        try {

            if (!TextUtils.isEmpty(rights)) {

                if (rights.contains("D"))
                    return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean haveAddRights(String rights) {

        try {

            if (!TextUtils.isEmpty(rights)) {

                if (rights.contains("A"))
                    return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
