package ru.skillbranch.gameofthrones.ui.houses

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

// TODO использовать дальше в адаптере для CertStorage, переопределив positionWithoutLeftMargin
class VerticalItemDecoration(context: Context, private val leftMargin: Int) : DividerItemDecoration(context, VERTICAL) {
    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val divider = drawable ?: return

        canvas.save()
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val adapterPosition = parent.getChildAdapterPosition(child)
            val withoutLeftMargin = positionWithoutLeftMargin(parent, adapterPosition)
            drawBound(canvas, parent, child, withoutLeftMargin, divider)
        }
        canvas.restore()
    }

    private fun positionWithoutLeftMargin(parent: RecyclerView, position: Int): Boolean = position == parent.adapter?.itemCount?.minus(1)

    private fun drawBound(canvas: Canvas, parent: RecyclerView, view: View, withoutLeftMargin: Boolean, divider: Drawable) {
        val bounds = Rect()
        parent.getDecoratedBoundsWithMargins(view, bounds)

        val left = if (withoutLeftMargin) 0 else leftMargin
        val bottom = bounds.bottom + view.translationY.roundToInt()
        val top = bottom - divider.intrinsicHeight

        divider.setBounds(left, top, parent.width, bottom)
        divider.draw(canvas)
    }
}