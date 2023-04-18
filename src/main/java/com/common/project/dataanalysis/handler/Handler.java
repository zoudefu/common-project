package com.common.project.dataanalysis.handler;

import com.common.project.dataanalysis.entity.DataSoureConfig;

public abstract class Handler<T> {
    
 protected Handler<T> next;
 
 public Handler<T> next(Handler<T> next) {
     this.next = next;
     return next;
 }
 public abstract void doHandler(DataSoureConfig dataSoureConfig);
 public static class Builder<T> {
     private Handler<T> head;
     private Handler<T> tail;
     public Builder<T> addHandler(Handler<T> handler) {
         if (this.head == null) {
             this.head = this.tail = handler;
             return this;
         }
         this.tail.next(handler);
         this.tail = handler;
         return this;
     }
     public Handler<T> build() {
         return this.head;
     }
 }
}

