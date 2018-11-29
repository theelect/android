package com.tonyecoleelection.android.views.button

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.StringRes
import android.support.v7.widget.CardView
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.tonyecoleelection.android.R
import com.tonyecoleelection.android.utils.dpToPx
import org.jetbrains.anko.textColor

/**
 * Created by aliumujib on 09/06/2018.
 */

class CardButtonView : FrameLayout {

    private var view: View
    private var title: TextView
    private var image: ImageView
    private var card: CardView

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        view = inflater.inflate(R.layout.card_btn_layout, this, true)

        val a = context.obtainStyledAttributes(attrs, R.styleable.CardButtonView, 0, 0)

        val text: String? = a.getString(R.styleable.CardButtonView_btnTitleText)
        val drawable: Drawable? = a.getDrawable(R.styleable.CardButtonView_imageSrc)
        val btnTextColor: Int? = a.getColor(R.styleable.CardButtonView_btnTextColor, 0)
        val cardBackGroundColor: Int? = a.getColor(R.styleable.CardButtonView_btnBackgroundColor, 0)
        val cardElevation: Int = a.getDimensionPixelSize(R.styleable.CardButtonView_btnElevation, 2)
        val cardContentPadding: Int = a.getDimensionPixelSize(R.styleable.CardButtonView_btnContentPadding, 0)
        val cardContentVerticalPadding: Int = a.getDimensionPixelSize(R.styleable.CardButtonView_btnContentVerticalPadding, 0)
        val cardContentHorizontalPadding: Int = a.getDimensionPixelSize(R.styleable.CardButtonView_btnContentHorizontalPadding, 0)
        val textSize: Int = a.getDimensionPixelSize(R.styleable.CardButtonView_btnTextSize, 0)

        title = view.findViewById(R.id.text)
        image = view.findViewById(R.id.image)
        card = view.findViewById(R.id.card_view)


        try {
            setTitleText(text)

            if (cardElevation != 0) {
                card.cardElevation = context.dpToPx(cardElevation).toFloat()
            }

            if (cardContentPadding != 0) {
                card.setContentPadding(cardContentPadding, cardContentPadding, cardContentPadding, cardContentPadding)
            }

            if ((cardContentHorizontalPadding != 0) and (cardContentVerticalPadding != 0)) {
                card.setContentPadding(cardContentHorizontalPadding, cardContentVerticalPadding,
                        cardContentHorizontalPadding, cardContentVerticalPadding)
            }

            if (drawable != null) {
                image.visibility = View.VISIBLE
                title.gravity = Gravity.START
                image.setImageDrawable(drawable)
            } else {
                image.visibility = View.GONE
                title.gravity = Gravity.CENTER
            }


            if (btnTextColor != 0) {
                title.textColor = btnTextColor!!
            }



            if (cardBackGroundColor != 0) {
                card.setCardBackgroundColor(cardBackGroundColor!!)
            }

            if (textSize != 0) {
                title.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }

        card.post { card.radius = (card.height / 2).toFloat() }

        a.recycle()
    }

    public fun setTitleText(titleText: String?) {
        title.text = titleText
    }

    public fun setTitleText(@StringRes titleText: Int) {
        title.setText(titleText)
    }


    override fun setOnClickListener(l: OnClickListener?) {
        super.setOnClickListener(l)
        title.setOnClickListener(l)
    }

}