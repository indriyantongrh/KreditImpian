package com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.application.kreditimpian.FormPengajuan.UpgradeImpian.repository.UpgradeImpianRepository;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Context context;

    public ViewModelFactory(Context context) {
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UpgradeImpianViewModel.class)) {
            UpgradeImpianRepository upgradeImpianRepository = new UpgradeImpianRepository(context);
            return (T) new UpgradeImpianViewModel(upgradeImpianRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
