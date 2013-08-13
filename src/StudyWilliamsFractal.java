/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import org.apache.log4j.Logger;
import studylib.sdk.*;
import studylib.sdk.studies.BarState;
import studylib.sdk.studies.SeriesExpr;

import java.util.ArrayList;
import java.util.List;

@StudyDecl(
    studyId = "WilliamsFractal",
    isPriceStudy = true,
    title = "Williams Fractal",
    shortTitle = "Fractal",
    isLinkedToSeries = true)
public class StudyWilliamsFractal extends Study
{
    final private static Logger logger = Logger.getLogger("basicstudies." + StudyWilliamsFractal.class.getName());

    @StudyGraphicsShapeMark(id="upFractal", title="Up fractals", color="green",
                            shape=CanvasEx.ShapeMarkType.ArrowUp, location=CanvasEx.MarkLocation.AboveBar,
                            halign=CanvasEx.HAlign.CENTER)
    List<JsonObj.ShapeMark> upFractal = new ArrayList<>();

    @StudyGraphicsShapeMark(id="downFractal", title="Down fractals", color="red",
                            shape=CanvasEx.ShapeMarkType.ArrowDown, location=CanvasEx.MarkLocation.BelowBar,
                            halign=CanvasEx.HAlign.CENTER)
    List<JsonObj.ShapeMark> downFractal = new ArrayList<>();

    @Override
    public void init()
    {
        newSeries(new SeriesExpr(barSet())
        {
            final int maxDepth = 8;

            final Series high = using(high(), maxDepth);
            final Series low = using(low(), maxDepth);
            final TimeScale time = using(barSet().timescale(), maxDepth);

            @Override
            public void update(int i, BarState state)
            {
                if (i < maxDepth)
                {
                    return;
                }

                if (!state.isConfirmedBar())
                {
                    return;
                }

                calcUpFractals(i - 2);
                calcDownFractals(i - 2);
            }

            private void calcUpFractals(int i)
            {
                double currentHigh = high.at(i);
                if (currentHigh > high.at(i - 1) &&
                    currentHigh > high.at(i - 2) &&
                    currentHigh > high.at(i + 1) &&
                    currentHigh > high.at(i + 2))
                {
                    addUpMark(time.timeAt(i));
                }
                else if (currentHigh == high.at(i - 1) &&
                         currentHigh > high.at(i - 2) &&
                         currentHigh > high.at(i - 3) &&
                         currentHigh > high.at(i + 1) &&
                         currentHigh > high.at(i + 2))
                {
                    addUpMark(time.timeAt(i));
                }
                else if (currentHigh >=  high.at(i - 1) &&
                         currentHigh == high.at(i - 2) &&
                         currentHigh > high.at(i - 3) &&
                         currentHigh > high.at(i - 4) &&
                         currentHigh > high.at(i + 1) &&
                         currentHigh > high.at(i + 2))
                {
                    addUpMark(time.timeAt(i));
                }
                else if (currentHigh >= high.at(i - 1) &&
                         currentHigh == high.at(i - 2) &&
                         currentHigh == high.at(i - 3) &&
                         currentHigh > high.at(i - 4) &&
                         currentHigh > high.at(i - 5) &&
                         currentHigh > high.at(i + 1) &&
                         currentHigh > high.at(i + 2))
                {
                    addUpMark(time.timeAt(i));
                }
                else if (currentHigh >= high.at(i - 1) &&
                         currentHigh == high.at(i - 2) &&
                         currentHigh >= high.at(i - 3) &&
                         currentHigh == high.at(i - 4) &&
                         currentHigh > high.at(i - 5) &&
                         currentHigh > high.at(i - 6) &&
                         currentHigh > high.at(i + 1) &&
                         currentHigh > high.at(i + 2))
                {
                    addUpMark(time.timeAt(i));
                }
            }

            private void calcDownFractals(int i)
            {
                double currentLow = low.at(i);
                if (currentLow < low.at(i - 1) &&
                    currentLow < low.at(i - 2) &&
                    currentLow < low.at(i + 1) &&
                    currentLow < low.at(i + 2))
                {
                    addDownMark(time.timeAt(i));
                }
                else if (currentLow == low.at(i - 1) &&
                         currentLow < low.at(i - 2) &&
                         currentLow < low.at(i - 3) &&
                         currentLow < low.at(i + 1) &&
                         currentLow < low.at(i + 2))
                {
                    addDownMark(time.timeAt(i));
                }
                else if (currentLow <= low.at(i - 1) &&
                         currentLow == low.at(i - 2) &&
                         currentLow < low.at(i - 3) &&
                         currentLow < low.at(i - 4) &&
                         currentLow < low.at(i + 1) &&
                         currentLow < low.at(i + 2))
                {
                    addDownMark(time.timeAt(i));
                }
                else if (currentLow <= low.at(i - 1) &&
                         currentLow == low.at(i - 2) &&
                         currentLow == low.at(i - 3) &&
                         currentLow < low.at(i - 4) &&
                         currentLow < low.at(i - 5) &&
                         currentLow < low.at(i + 1) &&
                         currentLow < low.at(i + 2))
                {
                    addDownMark(time.timeAt(i));
                }
                else if (currentLow <= low.at(i - 1) &&
                         currentLow == low.at(i - 2) &&
                         currentLow <= low.at(i - 3) &&
                         currentLow == low.at(i - 4) &&
                         currentLow < low.at(i - 5) &&
                         currentLow < low.at(i - 6) &&
                         currentLow < low.at(i + 1) &&
                         currentLow < low.at(i + 2))
                {
                    addDownMark(time.timeAt(i));
                }
            }
            private void addUpMark(Long time)
            {
                upFractal.add(new JsonObj.ShapeMark(time, ""));
            }
            private void addDownMark(Long time)
            {
                downFractal.add(new JsonObj.ShapeMark(time, ""));
            }
        });
    }
}
