package com.validatorcrawler.aliazaz

import android.view.View
import android.view.ViewGroup
import android.widget.*


class Clear {

    companion object {

        @JvmOverloads
        @JvmStatic
        fun clearCheckBoxes(container: ViewGroup, flag: Boolean? = null) {
            for (i in 0 until container.childCount) {
                when (val v: View = container.getChildAt(i)) {
                    is CheckBox -> {
                        v.isChecked = false
                        v.error = null
                        flag?.let { v.setEnabled(flag) }
                    }
                    else -> clearAllFields(v, flag)
                }
            }
        }

        @JvmOverloads
        @JvmStatic
        fun clearRadioGroup(view: RadioGroup, flag: Boolean? = null) {
            view.clearCheck()
            for (i in 0 until view.childCount) {
                when (val v: View = view.getChildAt(i)) {
                    is RadioButton -> {
                        v.clearFocus()
                        v.error = null
                        flag?.let { v.setEnabled(flag) }
                    }
                    else -> clearAllFields(v, flag)
                }
            }
        }

        @JvmOverloads
        @JvmStatic
        fun clearAllFields(view: View, flag: Boolean? = null) {
            when (view) {

                is EditText -> {
                    view.text = null
                    view.clearFocus()
                    view.error = null
                    flag?.let { view.setEnabled(flag) }
                }

                is CheckBox -> {
                    view.isChecked = false
                    view.error = null
                    flag?.let { view.setEnabled(flag) }
                }

                is Spinner -> {
                    view.setSelection(0)
                    flag?.let { view.setEnabled(flag) }
                }

                is Switch -> {
                    view.isChecked = false
                    view.error = null
                    flag?.let { view.setEnabled(flag) }
                }

                is RadioGroup -> clearRadioGroup(view, flag)

                is RadioButton -> flag?.let { view.setEnabled(flag) }

                is ViewGroup -> when (view.tag) {
                    "0" -> clearCheckBoxes(view, flag)
                    else -> {
                        for (i in 0 until view.childCount) {
                            val v = view.getChildAt(i)
                            clearAllFields(v, flag)
                        }
                    }
                }

            }
        }
    }
}