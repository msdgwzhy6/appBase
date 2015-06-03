package com.snicesoft.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;

public class SniceRadioGroup extends android.widget.RadioGroup {

	public SniceRadioGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SniceRadioGroup(Context context) {
		super(context);
	}

	public void checked(int id, boolean checked) {
		View checkedView = findViewById(id);
		if (checkedView != null && checkedView instanceof RadioButton) {
			((RadioButton) checkedView).setChecked(checked);
		}
	}
}
