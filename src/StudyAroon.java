/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.Extremes;
import studylib.sdk.studies.Pivot;

@StudyDecl(studyId = "AROON", title = "Aroon", shortTitle = "Aroon")
public class StudyAroon extends Study
{
    @StudyArgSourceLength(name = "length", defval=14)
    int length;

    @StudyPlot(name = "AroonUp")
    @StudyPlotStyle(title = "Aroon Up", color = "#FF6A00")
    Series upper;

    @StudyPlot(name = "AroonDown")
    @StudyPlotStyle(title = "Aroon Down", color = "#0094FF")
    Series lower;

    @Override
    public void init()
    {
        lower = newSeries(new Aroon(length, Pivot.Type.LOW, low()));
        upper = newSeries(new Aroon(length, Pivot.Type.HIGH, high()));
    }
    
    static public class Aroon extends Extremes
    {
        public Aroon(int length, Pivot.Type type, Series source)
        {
            super(length, type, source);
        }

        @Override
        public double calc(int i, boolean isNewVal)
        {
            pivot.update(i, isNewVal);
            if (i < pivot.areaRight())
            {
                return Double.NaN;
            }
            final int length = 1 + pivot.areaRight();
            final int pivot = this.pivot.currentIndex();
            return 100.0 * (pivot + length - i) / length;
        }
    }
}
