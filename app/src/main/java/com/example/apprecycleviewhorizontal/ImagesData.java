package com.example.apprecycleviewhorizontal;

import java.util.ArrayList;

public class ImagesData {

    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mHarga = new ArrayList<>();

    public void initImageBitmaps() {

        mImageUrls.add("https://www.satubaju.com/img/editor/img_iscums/706/2375706_l.jpg");
        mTitles.add("Kaos Malang");
        mHarga.add("100000");

        mImageUrls.add("https://ciptaloka-mockups.s3.amazonaws.com/design/qm45w9de4n-21-0-1--l.jpg");
        mTitles.add("Kaos Jember");
        mHarga.add("90000");

        mImageUrls.add("https://ciptaloka-mockups.s3.amazonaws.com/design/bno8n66o79-39-0-1--l.jpg");
        mTitles.add("Kaos Surabaya");
        mHarga.add("80000");

        mImageUrls.add("https://www.tokome.id/images/product/wisatawan/TST003154/1/c.jpg");
        mTitles.add("Kaos Batu");
        mHarga.add("70000");

        mImageUrls.add("https://ciptaloka-mockups.s3.amazonaws.com/design/nyzrg67845-18-0-2--l.jpg");
        mTitles.add("Kaos Banyuwangi");
        mHarga.add("60000");

        mImageUrls.add("https://www.satubaju.com/img/editor/img_iscums/610/2318610_m.jpg");
        mTitles.add("Kaos Bandung");
        mHarga.add("50000");

        mImageUrls.add("https://www.satubaju.com/img/editor/img_iscums/615/97615_l.jpg");
        mTitles.add("Kaos Bali");
        mHarga.add("40000");

        mImageUrls.add("https://ecs7.tokopedia.net/img/cache/700/product-1/2015/5/8/429758/429758_c3cc1104-f4de-11e4-831d-892887772fba.jpg");
        mTitles.add("Kaos Jogjakarta");
        mHarga.add("30000");

        mImageUrls.add("https://www.static-src.com/wcsstore/Indraprastha/images/catalog/medium/vm-alshop_vm-ct06-o-neck-jakarta-katun-putih-kaos-pria_full01.jpg");
        mTitles.add("Kaos Jakarta");
        mHarga.add("20000");

        mImageUrls.add("https://ciptaloka-mockups.s3.amazonaws.com/design/rw4vgjnk47-213-0-1--l.jpg");
        mTitles.add("Kaos Papua");
        mHarga.add("10000");

        mImageUrls.add("https://ecs7.tokopedia.net/img/cache/700/product-1/2018/2/27/3285685/3285685_25981006-8403-4f15-b3f1-c14f9eeec3f0_1299_1772.jpg");
        mTitles.add("Kaos Riyadh");
        mHarga.add("110000");

    }

    public ArrayList<String> getmTitles() {
        return mTitles;
    }

    public ArrayList<String> getmImageUrls() {
        return mImageUrls;
    }

    public String getImagePosition(int position) {
        return mImageUrls.get(position);

    }

    public String getHarga(int position) {return mHarga.get(position); }
}
