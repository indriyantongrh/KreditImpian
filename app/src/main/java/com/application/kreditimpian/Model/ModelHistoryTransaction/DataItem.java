package com.application.kreditimpian.Model.ModelHistoryTransaction;


import com.google.gson.annotations.SerializedName;


public class DataItem{

	@SerializedName("id_merchant")
	private String id_merchant;

	@SerializedName("name_merchant")
	private String name_merchant;

	@SerializedName("id_company")
	private String id_company;



	@SerializedName("name_company")
	private String name_company;

	@SerializedName("expires")
	private Object expires;

	@SerializedName("downloadable")
	private String downloadable;

	@SerializedName("id_transactions")
	private String idTransactions;

	@SerializedName("visibility")
	private String visibility;

	@SerializedName("description")
	private String description;

	@SerializedName("discount")
	private String discount;

	@SerializedName("deliverable")
	private String deliverable;

	@SerializedName("id_product_category")
	private String idProductCategory;

	@SerializedName("price_capital")
	private String priceCapital;

	@SerializedName("number")
	private String number;

	@SerializedName("condition")
	private String condition;

	@SerializedName("id_product")
	private String idProduct;

	@SerializedName("filename")
	private String filename;

	@SerializedName("price_sale")
	private String priceSale;

	@SerializedName("target_age")
	private String targetAge;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private String id;

	@SerializedName("id_currency")
	private String idCurrency;

	@SerializedName("sku")
	private String sku;

	@SerializedName("stock")
	private String stock;

	@SerializedName("slug")
	private String slug;

	@SerializedName("status")
	private String status;

	@SerializedName("timestamp")
	private Object timestamp;

	@SerializedName("target_gender")
	private String targetGender;

	@SerializedName("note")
	private String note;

	@SerializedName("tenor")
	private String tenor;

	@SerializedName("down_payment")
	private String down_payment;

	@SerializedName("id_creditor")
	private String id_creditor;

	@SerializedName("postal_fee")
	private String postal_fee;

	@SerializedName("shipping")
	private String shipping;

	@SerializedName("payment_method")
	private String payment_method;

	@SerializedName("installment")
	private String installment;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTenor() {
		return tenor;
	}

	public void setTenor(String tenor) {
		this.tenor = tenor;
	}

	public String getDown_payment() {
		return down_payment;
	}

	public void setDown_payment(String down_payment) {
		this.down_payment = down_payment;
	}

	public String getId_creditor() {
		return id_creditor;
	}

	public void setId_creditor(String id_creditor) {
		this.id_creditor = id_creditor;
	}

	public String getPostal_fee() {
		return postal_fee;
	}

	public void setPostal_fee(String postal_fee) {
		this.postal_fee = postal_fee;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getInstallment() {
		return installment;
	}

	public void setInstallment(String installment) {
		this.installment = installment;
	}

	public String getTotal_pembayaran() {
		return total_pembayaran;
	}

	public void setTotal_pembayaran(String total_pembayaran) {
		this.total_pembayaran = total_pembayaran;
	}

	@SerializedName("total_pembayaran")
	private String total_pembayaran;

	public void setExpires(Object expires){
		this.expires = expires;
	}

	public Object getExpires(){
		return expires;
	}

	public void setDownloadable(String downloadable){
		this.downloadable = downloadable;
	}

	public String getDownloadable(){
		return downloadable;
	}

	public void setIdTransactions(String idTransactions){
		this.idTransactions = idTransactions;
	}

	public String getIdTransactions(){
		return idTransactions;
	}

	public void setVisibility(String visibility){
		this.visibility = visibility;
	}

	public String getVisibility(){
		return visibility;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setDiscount(String discount){
		this.discount = discount;
	}

	public String getDiscount(){
		return discount;
	}

	public void setDeliverable(String deliverable){
		this.deliverable = deliverable;
	}

	public String getDeliverable(){
		return deliverable;
	}

	public void setIdProductCategory(String idProductCategory){
		this.idProductCategory = idProductCategory;
	}

	public String getIdProductCategory(){
		return idProductCategory;
	}

	public void setPriceCapital(String priceCapital){
		this.priceCapital = priceCapital;
	}

	public String getPriceCapital(){
		return priceCapital;
	}

	public void setNumber(String number){
		this.number = number;
	}

	public String getNumber(){
		return number;
	}

	public void setCondition(String condition){
		this.condition = condition;
	}

	public String getCondition(){
		return condition;
	}

	public void setIdProduct(String idProduct){
		this.idProduct = idProduct;
	}

	public String getIdProduct(){
		return idProduct;
	}

	public void setFilename(String filename){
		this.filename = filename;
	}

	public String getFilename(){
		return filename;
	}

	public void setPriceSale(String priceSale){
		this.priceSale = priceSale;
	}

	public String getPriceSale(){
		return priceSale;
	}

	public void setTargetAge(String targetAge){
		this.targetAge = targetAge;
	}

	public String getTargetAge(){
		return targetAge;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setIdCurrency(String idCurrency){
		this.idCurrency = idCurrency;
	}

	public String getIdCurrency(){
		return idCurrency;
	}

	public void setSku(String sku){
		this.sku = sku;
	}

	public String getSku(){
		return sku;
	}

	public void setStock(String stock){
		this.stock = stock;
	}

	public String getStock(){
		return stock;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setTimestamp(Object timestamp){
		this.timestamp = timestamp;
	}

	public Object getTimestamp(){
		return timestamp;
	}

	public void setTargetGender(String targetGender){
		this.targetGender = targetGender;
	}

	public String getTargetGender(){
		return targetGender;
	}

	public String getId_merchant() {
		return id_merchant;
	}

	public void setId_merchant(String id_merchant) {
		this.id_merchant = id_merchant;
	}

	public String getName_merchant() {
		return name_merchant;
	}

	public void setName_merchant(String name_merchant) {
		this.name_merchant = name_merchant;
	}

	public String getId_company() {
		return id_company;
	}

	public void setId_company(String id_company) {
		this.id_company = id_company;
	}

	public String getName_company() {
		return name_company;
	}

	public void setName_company(String name_company) {
		this.name_company = name_company;
	}
}