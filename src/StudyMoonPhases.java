/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.BarState;
import studylib.sdk.studies.MoonPhaseCalculator;
import studylib.sdk.studies.SeriesExpr;
import java.util.ArrayList;
import java.util.List;


@StudyDecl(
        studyId = "MoonPhases",
        isPriceStudy = true,
        isLinkedToSeries = true,
        title = "Moon Phases",
        shortTitle = "MP",
        supportedResolutions = {
                Resolution.Type.intraday,
                Resolution.Type.daily,
                Resolution.Type.weekly
        })
public class StudyMoonPhases extends Study
{
    @StudyGraphicsBackground(id = "backgroundGrowingMoon", title = "Growing moon",
        color = "#4098ee", borderColor = "blue")
    final List<JsonObj.BackgroundItem> backgroundGrowingMoon = new ArrayList<>();

    @StudyGraphicsBackground(id = "backgroundWaningMoon", title = "Waning moon",
        color = "#8a9695", borderColor = "blue")
    final List<JsonObj.BackgroundItem> backgroundWaningMoon = new ArrayList<>();


    @StudyGraphicsShapeMark(id = "newMoon", title = "New moon mark", color = "#eeeeee", borderColor = "black",
        location = CanvasEx.MarkLocation.AboveBar, shape = CanvasEx.ShapeMarkType.Circle, halign=CanvasEx.HAlign.LEFT)
    final  List<JsonObj.ShapeMark> marksNewMoon = new ArrayList<>();

    @StudyGraphicsShapeMark(id = "fullMoon", title = "Full moon mark", color = "#505050", borderColor = "black",
        location = CanvasEx.MarkLocation.BelowBar, shape = CanvasEx.ShapeMarkType.Circle, halign=CanvasEx.HAlign.LEFT)
    final  List<JsonObj.ShapeMark> marksFullMoon = new ArrayList<>();


    @Override
    public void init()
    {
        newSeries(new SeriesExpr(barSet())
        {
            final TimeScale timeScale = using(timescale(), 2);
            JsonObj.MoonPhaseTimePoint prevMoonPhasePoint = null;
            Long prevMoonTime = null;
            final JsonObj.BackgroundItem unconfirmed = new JsonObj.BackgroundItem(0, 0);

            @Override
            public void update(int i, BarState state)
            {
                if (!state.isNewBar())
                {
                    return;
                }
                final long time = timeScale.timeAt(i);
                if (state.isLastBar())
                {
                    backgroundGrowingMoon.remove(unconfirmed);
                    backgroundWaningMoon.remove(unconfirmed);
                }
                final JsonObj.MoonPhaseTimePoint moonPhasePoint = MoonPhaseCalculator.getLastNewMoon(time);
                if (prevMoonPhasePoint == null)
                {
                    prevMoonPhasePoint = moonPhasePoint;
                    prevMoonTime = time;
                }
                if (!moonPhasePoint.equals(prevMoonPhasePoint))
                {
                    if (prevMoonPhasePoint.state.equals(JsonObj.MoonPhase.NewMoon))
                    {
                        backgroundGrowingMoon.add(new JsonObj.BackgroundItem(prevMoonTime, timeScale.timeAt(i - 1)));
                        marksFullMoon.add(new JsonObj.ShapeMark(time, "Full moon"));
                    }
                    else
                    {
                        backgroundWaningMoon.add(new JsonObj.BackgroundItem(prevMoonTime, timeScale.timeAt(i - 1)));
                        marksNewMoon.add(new JsonObj.ShapeMark(time, "New moon"));
                    }
                    prevMoonPhasePoint = moonPhasePoint;
                    prevMoonTime = time;
                }
                if (state.isLastBar())
                {
                    unconfirmed.set(prevMoonTime, time);
                    if (prevMoonPhasePoint.state.equals(JsonObj.MoonPhase.NewMoon))
                    {
                        backgroundGrowingMoon.add(unconfirmed);
                    }
                    else
                    {
                        backgroundWaningMoon.add(unconfirmed);
                    }
                }
            }
        });
    }
}
