package com.jhostinluna.sprint4.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.jhostinluna.sprint4.R
import com.jhostinluna.sprint4.databinding.LinearLayoutDetailPersonBinding

class LayoutItemPerson @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
): LinearLayout(context,attributeSet,defStyleAttr) {
    var binding: LinearLayoutDetailPersonBinding
    init {
        binding = LinearLayoutDetailPersonBinding.inflate(LayoutInflater.from(context),this,true)
        val atributes = context.obtainStyledAttributes(attributeSet, R.styleable.LayoutItemPerson)
        binding.imageViewPersonCustom.setImageDrawable(atributes.getDrawable(R.styleable.LayoutItemPerson_image))
        binding.textName.text = atributes.getText(R.styleable.LayoutItemPerson_textName)
        binding.textVNameValue.text = atributes.getText(R.styleable.LayoutItemPerson_textValue)
        atributes.recycle()
    }
}