package me.thejaao.mobcoins.helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BalanceHelper {

    private static String[] FORMATS = {
            "-", "-", "K", "M", "B", "T", "Q", "QQ", "S", "SS",
            "OC", "N", "D", "UN", "DD", "TR", "QT", "QN", "SD", "SPD",
            "OD", "ND", "VG", "UVG", "DVG", "TVG", "QTV", "QNV", "SEV", "SPV",
            "OVG", "NVG", "TD"
    };

    public static String balance(double balance) {
        boolean isWholeNumber = balance == Math.round(balance);

        DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        formatSymbols.setDecimalSeparator('.');

        String pattern = isWholeNumber ? "###,###.###" : "###,##0.00";

        DecimalFormat decimalFormat = new DecimalFormat(pattern, formatSymbols);
        return decimalFormat.format(balance);
    }

    public static String prefix(double value){
        String decimalFormatted = new DecimalFormat("#,###").format(value).replace(".", ",");

        int indexOf = decimalFormatted.indexOf(","), i = decimalFormatted.split(",").length;

        if (indexOf == -1) {
            return decimalFormatted;
        }

        return (decimalFormatted.substring(0, indexOf + 2) + FORMATS[i]).replace(",0", "");
    }
}
