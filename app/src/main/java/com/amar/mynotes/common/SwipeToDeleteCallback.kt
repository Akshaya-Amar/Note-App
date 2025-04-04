package com.amar.mynotes.common

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.amar.mynotes.R

class SwipeToDeleteCallback(
     private val onDelete: (Int) -> Unit
) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
     override fun onMove(
          recyclerView: RecyclerView,
          viewHolder: RecyclerView.ViewHolder,
          target: RecyclerView.ViewHolder
     ): Boolean {
          return false
     }

     override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
          val position = viewHolder.adapterPosition
          onDelete(position)
     }

     override fun onChildDraw(
          canvas: Canvas,
          recyclerView: RecyclerView,
          viewHolder: RecyclerView.ViewHolder,
          dX: Float,
          dY: Float,
          actionState: Int,
          isCurrentlyActive: Boolean
     ) {
          super.onChildDraw(
               canvas,
               recyclerView,
               viewHolder,
               dX,
               dY,
               actionState,
               isCurrentlyActive
          )

          if (isCurrentlyActive) {
               val itemView = viewHolder.itemView
               drawSwipeBackground(canvas, itemView, dX)
               drawDeleteIcon(canvas, itemView, dX)
          }
     }

     private fun drawDeleteIcon(canvas: Canvas, itemView: View, dX: Float) {
          val delIcon: Drawable? = ContextCompat.getDrawable(itemView.context, R.drawable.baseline_delete_white_48)
          delIcon?.let { deleteIcon ->
               val iconMargin = 20
               val iconWidth = deleteIcon.intrinsicWidth / 1.5f
               val iconHeight = deleteIcon.intrinsicHeight / 1.5f

               val iconLeft: Float
               val iconTop: Float
               if (dX < 0) {
                    iconLeft = itemView.right - iconWidth - iconMargin.toFloat()
                    iconTop = itemView.top + (itemView.height - iconHeight) / 2f
               } else {
                    iconLeft = itemView.left + iconMargin.toFloat()
                    iconTop = itemView.top + (itemView.height - iconHeight) / 2f
               }

               deleteIcon.setBounds(
                    iconLeft.toInt(),
                    iconTop.toInt(),
                    (iconLeft + iconWidth).toInt(),
                    (iconTop + iconHeight).toInt()
               )
               deleteIcon.draw(canvas)
          }
     }

     private fun drawSwipeBackground(canvas: Canvas, itemView: View, dX: Float) {
          val paint = Paint().apply {
               color = ContextCompat.getColor(itemView.context, android.R.color.holo_red_light)
               style = Paint.Style.FILL
          }

          if (dX > 0) {
               canvas.drawRect(
                    itemView.left.toFloat(),
                    itemView.top.toFloat(),
                    itemView.left + dX,
                    itemView.bottom.toFloat(),
                    paint
               )
          } else if (dX < 0) {
               canvas.drawRect(
                    itemView.right + dX,
                    itemView.top.toFloat(),
                    itemView.right.toFloat(),
                    itemView.bottom.toFloat(),
                    paint
               )
          }
     }
}