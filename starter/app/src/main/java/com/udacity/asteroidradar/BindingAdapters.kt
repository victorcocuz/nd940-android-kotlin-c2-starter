package com.udacity.asteroidradar

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.udacity.asteroidradar.domain.Asteroid

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

// Main fragment Picture Of The Day
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Picasso.with(imgView.context).load(imgUri)
            .placeholder(R.drawable.placeholder_picture_of_day)
            .error(R.drawable.placeholder_picture_of_day).into(imgView)
    }
}

// Main fragment Asteroid data
@BindingAdapter("asteroidName")
fun TextView.setAsteroidName(item: Asteroid?) {
    item?.let {
        text = item.codeName
    }
}

@BindingAdapter("asteroidDate")
fun TextView.setAsteroidDate(item: Asteroid?) {
    item?.let {
        text = item.closeApproachDate
    }
}

@BindingAdapter("asteroidPotentiallyHazardous")
fun ImageView.setAsteroidIsPotentiallyDangerous(item: Asteroid?) {
    item?.let {
        setImageResource(
            when (item.isPotentiallyHazardous) {
                true -> R.drawable.ic_status_potentially_hazardous
                false -> R.drawable.ic_status_normal
            }
        )
    }
}
