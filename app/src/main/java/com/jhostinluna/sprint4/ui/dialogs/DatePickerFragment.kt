package com.jhostinluna.sprint4.ui.dialogs

import android.app.DatePickerDialog
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment

class DatePickerFragment: DialogFragment(), DatePickerDialog.OnDateSetListener {
    interface DatePickerListener {
        fun onDateSelected(year: Int, month: Int, day: Int)
    }
    lateinit var datePickerListener: DatePickerListener
    fun setDatePickerListeners(listener: DatePickerListener) {
        datePickerListener = listener
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(requireActivity(), this, year, month, day)

    }
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        datePickerListener.onDateSelected(year, month, dayOfMonth)
        dismiss()
    }
}