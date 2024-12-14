package com.example.task_046

class ContactViewModal (
    val name: String? = null,
    val telephone: String? = null
){
    override fun toString(): String {
        return "$name, $telephone"
    }
}