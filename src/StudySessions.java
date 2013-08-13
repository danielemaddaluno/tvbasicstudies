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

@StudyDecl(studyId = "Sessions", isPriceStudy = true, isLinkedToSeries = true,
           title = "Sessions", shortTitle = "Sessions", supportedResolutions = {Resolution.Type.intraday})
public class StudySessions extends Study
{
    private static final Logger logger =
            Logger.getLogger("basicstudies." + StudySessions.class.getName());

    @StudyGraphicsBackground(id = "odd", title = "Odd", color = "lime", borderColor = "blue")
    final List<JsonObj.BackgroundItem> oddSessions = new ArrayList<>();

    @StudyGraphicsBackground(id = "even", title = "Even", color = "red", borderColor = "blue")
    final List<JsonObj.BackgroundItem> evenSessions = new ArrayList<>();


    @Override
    public void init()
    {
        final BarBuilder barBuilder = barBuilder(resolution());
        newSeries(new SeriesExpr(barSet())
        {
            final TimeScale timeScale = using(timescale(), 2);
            boolean isSessionOdd = true;
            JsonObj.BackgroundItem lastBg = null;
            int prevIndex = BarBuilder.PRE_SESSION;

            @Override
            public void update(int i, BarState state)
            {
                final long time = timeScale.timeAt(i);
                int index = barBuilder.indexOfBar(time);

                //logger.debug(barSet().src().toString() + ", i=" + i + ", UTC time=" + Time.toString(time * 1000) + ", prevIndex=" + index);

                if (prevIndex == index)
                {
                    return;
                }

                if (index == BarBuilder.POST_SESSION)
                {
                    barBuilder.moveTo(time);
                    index = barBuilder.indexOfBar(time);
                    prevIndex = BarBuilder.PRE_SESSION;
                    lastBg = null;
                    if (index == BarBuilder.POST_SESSION)
                    {
                        return;
                    }
                }

                if (index < 0)
                {
                    return;
                }

                prevIndex = index;

                if (lastBg == null)
                {
                    lastBg = new JsonObj.BackgroundItem(time, time);
                    if (isSessionOdd)
                    {
                        oddSessions.add(lastBg);
                    }
                    else
                    {
                        evenSessions.add(lastBg);
                    }
                    isSessionOdd = !isSessionOdd;
                }
                else
                {
                    lastBg.setStopTime(time);
                }
            }
        });
    }
}
