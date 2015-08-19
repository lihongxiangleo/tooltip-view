package com.venmo.view;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

import static android.graphics.Path.Direction;

public class TopArrowLocation implements ArrowLocation {

    @Override
    public void draw(TooltipView view, Canvas canvas) {
        view.tooltipPath = new Path();
        RectF rectF = new RectF(canvas.getClipBounds());
        rectF.top += view.getArrowHeight();

        view.tooltipPath.addRoundRect(rectF, view.getCornerRadius(), view.getCornerRadius(), Direction.CW);

        float middle = rectF.width() / 2;
        if (view.getAnchoredViewId() != View.NO_ID) {
            View anchoredView = ((View) view.getParent()).findViewById(view.getAnchoredViewId());
            middle += anchoredView.getX() + anchoredView.getWidth() / 2 - view.getX() - view.getWidth() / 2;
        }

        view.tooltipPath.moveTo(middle, 0f);
        int arrowDx = view.getArrowWidth() / 2;
        view.tooltipPath.lineTo(middle - arrowDx, rectF.top);
        view.tooltipPath.lineTo(middle + arrowDx, rectF.top);
        view.tooltipPath.close();

        view.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        view.paint.setColor(view.getTooltipColor());
    }
}
