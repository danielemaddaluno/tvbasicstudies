/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.BarState;
import studylib.sdk.studies.SeriesExpr;

import java.util.ArrayList;
import java.util.List;

@StudyDecl(isPriceStudy = true)
public class StudyPivotPointsStdNew extends Study
{
    private static class Data extends JsonObj
    {
        List<PivotPointStd> pivots = new ArrayList<>();
        public int findByStartDate(long time)
        {
            for (int i=0; i<pivots.size(); i++)
            {
                if (pivots.get(i).startTime == time)
                {
                    return i;
                }
            }
            return -1;
        }
    }

    @StudyOutputData
    Data data = new Data();

    @Override
    public void init()
    {
        timescale().setMinDepth(1);
        final StdLib lowResBarSet;
        final Resolution resolution = resolution();
        switch (resolution.type())
        {
            case intraday:
                lowResBarSet = overlay(ticker(), Resolution.days(1));
                break;
            case daily:
                lowResBarSet = overlay(ticker(), Resolution.months(1));
                break;
            case weekly:
            case monthly:
                lowResBarSet = overlay(ticker(), Resolution.months(12));
                break;
            default:
                return;
        }

        newSeries(new SeriesExpr(barSet())
        {
            final Series lowResHigh = using(adopt(lowResBarSet.high()), 2);
            final Series lowResLow = using(adopt(lowResBarSet.low()), 2);
            final Series lowResClose = using(adopt(lowResBarSet.close()), 2);
            final Series lowResTime = using(adopt(lowResBarSet.timescale()), 2);
            final Series hiResTime = using(timescale(), 2);
            Double prevTime = Double.NaN;

            @Override
            public void update(int i, BarState state)
            {
                if (((Double) lowResTime.at(i)).isNaN())
                {
                    return;
                }
                if (prevTime.isNaN())
                {
                    prevTime = lowResTime.at(i);
                }
                if ((prevTime != lowResTime.at(i)) || (i == lowResTime.length() - 1))
                {

                    final int index = (i == lowResTime.length() - 1) ? i : i - 1;
                    final int indexOfPivot = data.findByStartDate((long) lowResTime.at(index));
                    JsonObj.PivotPointStd pivotPoint = (indexOfPivot == -1) ?  new JsonObj.PivotPointStd() : data.pivots.get(indexOfPivot);
                    final double high = lowResHigh.at(index);
                    final double low = lowResLow.at(index);
                    final double pivot = (high + low + lowResClose.at(index)) / 3.0;
                    pivotPoint.p = pivot;
                    pivotPoint.r1 = pivot * 2 - low;
                    pivotPoint.s1 = pivot * 2 - high;
                    pivotPoint.r2 = pivot + (high - low);
                    pivotPoint.s2 = pivot - (high - low);
                    pivotPoint.r3 = pivot * 2 + (high - 2 * low);
                    pivotPoint.s3 = pivot * 2 - (2 * high - low);
                    pivotPoint.r4 = pivot + pivotPoint.r2 - pivotPoint.s2;
                    pivotPoint.s4 = pivot - pivotPoint.r2 + pivotPoint.s2;
                    pivotPoint.startTime = (long) lowResTime.at(index);
                    pivotPoint.endTime = (long) hiResTime.at(index);
                    if (indexOfPivot != -1)
                    {
                        data.pivots.add(pivotPoint);
                    }
                    prevTime = lowResTime.at(i);
                }
            }
        });
    }
}
