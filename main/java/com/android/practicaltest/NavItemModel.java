package com.android.practicaltest;


import android.graphics.drawable.Drawable;

public class NavItemModel {
    private String strStore;
    private String strCategory;
    private int micon;
    private int likeIcon;
    private int chatIcon;
    private String like;
    private String chat;


    public int getMicon() {
        return micon;
    }

    public void setMicon(int micon) {
        this.micon = micon;
    }

    public NavItemModel(int micon) {
        this.micon = micon;
    }

    public NavItemModel(String strStore, String strCategory, int micon, int likeIcon, int chatIcon, String like, String chat) {
        this.strStore = strStore;
        this.strCategory = strCategory;
        this.micon = micon;
        this.likeIcon = likeIcon;
        this.chatIcon = chatIcon;
        this.like = like;
        this.chat = chat;
    }

    public NavItemModel() {
    }

    public String getStrStore() {
        return strStore;
    }

    public void setStrStore(String strStore) {
        this.strStore = strStore;
    }

    public String getStrCategory() {
        return strCategory;
    }

    public void setStrCategory(String strCategory) {
        this.strCategory = strCategory;
    }

    public int getLikeIcon() {
        return likeIcon;
    }

    public void setLikeIcon(int likeIcon) {
        this.likeIcon = likeIcon;
    }

    public int getChatIcon() {
        return chatIcon;
    }

    public void setChatIcon(int chatIcon) {
        this.chatIcon = chatIcon;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}
