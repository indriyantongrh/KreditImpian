package com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.application.kreditimpian.FormPengajuan.UpgradeImpian.repository.UpgradeImpianRepository;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelUpgradeImpian;

import java.util.ArrayList;

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

    public LiveData<ArrayList<ModelUpgradeImpian>> pengajuanMotor() {
        return upgradeImpianRepository.pengajuanMotor(modelUpgradeImpian);
    }

    public LiveData<ArrayList<ModelUpgradeImpian>> pengajuanMobil() {
        return upgradeImpianRepository.pengajuanMobil(modelUpgradeImpian);
    }

    public LiveData<ArrayList<ModelUpgradeImpian>> pilihLeasing() {
        return upgradeImpianRepository.pilihLeasingg(modelUpgradeImpian);
    }

}
