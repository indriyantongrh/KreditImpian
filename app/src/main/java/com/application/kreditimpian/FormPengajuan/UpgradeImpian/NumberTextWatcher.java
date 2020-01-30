package com.application.kreditimpian.FormPengajuan.UpgradeImpian;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberTextWatcher implements TextWatcher {
    private final EditText editText;
    private String current = "";

    public NumberTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (!s.toString().equals(current)) {
            editText.removeTextChangedListener(this);

            Locale local = new Locale("id", "id");
            String replaceable = String.format("[Rp,.\\s]",
                    NumberFormat.getCurrencyInstance().getCurrency()
                            .getSymbol(local));
            String cleanString = s.toString().replaceAll(replaceable,
                    "");

            double parsed;
            try {
                parsed = Double.parseDouble(cleanString);
            } catch (NumberFormatException e) {
                parsed = 0.00;
            }

            NumberFormat formatter = NumberFormat
                    .getCurrencyInstance(local);
            formatter.setMaximumFractionDigits(0);
            formatter.setParseIntegerOnly(true);
            String formatted = formatter.format((parsed));

            String replace = String.format("[Rp\\s]",
                    NumberFormat.getCurrencyInstance().getCurrency()
                            .getSymbol(local));
            String clean = formatted.replaceAll(replace, "");

            current = formatted;
            editText.setText(clean);
            editText.setSelection(clean.length());
            editText.addTextChangedListener(this);
        }
    }
}
