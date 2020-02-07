package com.application.kreditimpian.FormPengajuan.UpgradeImpian.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.application.kreditimpian.FormPengajuan.UpgradeImpian.repository.UpgradeImpianRepository;
import com.application.kreditimpian.Model.ModelMitra;
import com.application.kreditimpian.Model.ModelNotifikasi;
import com.application.kreditimpian.Model.ModelProductNew.Category;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelMitraMultiguna;
import com.application.kreditimpian.Model.ModelUpgradeImpian.ModelUpgradeImpian;

import java.util.ArrayList;

public class UpgradeImpianViewModel extends ViewModel {
    private UpgradeImpianRepository upgradeImpianRepository;
    private ModelUpgradeImpian modelUpgradeImpian;
    private Category category;
    private ModelNotifikasi modelNotifikasi;

    public UpgradeImpianViewModel(UpgradeImpianRepository upgradeImpianRepository) {
        this.upgradeImpianRepository = upgradeImpianRepository;
    }

    public void setModelNotifikasi(ModelNotifikasi modelNotifikasi) {
        this.modelNotifikasi = modelNotifikasi;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setModelUpgradeImpian(ModelUpgradeImpian modelUpgradeImpian) {
        this.modelUpgradeImpian = modelUpgradeImpian;
    }

    public LiveData<ArrayList<ModelMitraMultiguna>> getMitraUpgradeImpian() {
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

    public LiveData<ArrayList<Category>> getKategoriFotoImpian() {
        return upgradeImpianRepository.getKategoriFotoImpian();
    }

    public LiveData<String> uploadFotoImpian() {
        return upgradeImpianRepository.uploadFotoImpian(category);
    }

    public LiveData<ArrayList<ModelNotifikasi>> getNotifikasi() {
        return upgradeImpianRepository.getNotifikasi(modelNotifikasi);
    }

    public LiveData<String> updateNotifikasi() {
        return upgradeImpianRepository.updateNotifikasi(modelNotifikasi);
    }

}
