import { Injectable } from '@angular/core';
import * as CryptoJS from 'crypto-js';
const SecureStorage = require('secure-web-storage');

export function getKey() {
  return atob(localStorage.getItem('session_id'));
}

@Injectable({
  providedIn: 'root',
})
export class LocalStorageService {
  public secureStorage = new SecureStorage(localStorage, {
    hash: function hash(key) {
      key = CryptoJS.SHA256(key, getKey());
      return key.toString();
    },

    encrypt: function encrypt(data) {
      data = CryptoJS.AES.encrypt(data, getKey());
      data = data.toString();
      return data;
    },

    decrypt: function decrypt(data) {
      data = CryptoJS.AES.decrypt(data, getKey());
      data = data.toString(CryptoJS.enc.Utf8);
      return data;
    },
  });

  constructor() {}

  getItem(key: string): any {
    // return JSON.parse(localStorage.getItem(key));
    return JSON.parse(this.secureStorage.getItem(key));
  }
  setItem(key: string, data: any) {
    // return localStorage.setItem(key, JSON.stringify(data));
    return this.secureStorage.setItem(key, JSON.stringify(data).toString());
  }
  removeItem(key: string) {
    // return localStorage.removeItem(key);
    return this.secureStorage.removeItem(key);
  }
  clear() {
    this.secureStorage.clear();
    localStorage.clear();
  }
}
