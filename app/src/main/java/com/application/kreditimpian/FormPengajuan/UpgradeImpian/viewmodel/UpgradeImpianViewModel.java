package com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.application.kreditimpian.FormPengajuan.UpgradeImpian.repository.UpgradeImpianRepository;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.Model.ModelUpgradeImpian;

import java.util.ArrayList;
import java.util.HashMap;

public class UpgradeImpianViewModel extends ViewModel {
    private UpgradeImpianRepository upgradeImpianRepository;
    private ModelUpgradeImpian modelUpgradeImpian;

    public UpgradeImpianViewModel(UpgradeImpianRepository upgradeImpianRepository) {
        this.upgradeImpianRepository = upgradeImpianRepository;
    }

    public void setModelUpgradeImpian(ModelUpgradeImpian modelUpgradeImpian) {
        this.modelUpgradeImpian = modelUpgradeImpian;
    }

    public LiveData<ArrayList<ModelMitra>> getMitraUpgradeImpian() {
        return upgradeImpianRepository.getMitraUpgrade();
    }
    public LiveData<HashMap> pengajuanMotor() {
        return upgradeImpianRepository.pengajuanMotor(modelUpgradeImpian);
    }
}
