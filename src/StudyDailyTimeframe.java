/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.BarState;
import studylib.sdk.studies.SeriesExpr;

@StudyDecl(supportedResolutions = {Resolution.Type.intraday})
public class StudyDailyTimeframe extends Study
{
    public static class Data extends JsonObj
    {
        public long startIndex;
        public long endIndex;
        public double highPrice;
        public double lowPrice;
        public double closePrice;

        public Data()
        {
            this.startIndex = 0;
            this.endIndex = 0;
            this.highPrice = Double.NaN;
            this.lowPrice = Double.NaN;
            this.closePrice = Double.NaN;
        }
    }

    @StudyOutputData
    Data data = new Data();

    @Override
    public void init()
    {
        final StdLib daily = overlay(ticker(), Resolution.days(1));
        newSeries(new SeriesExpr(barSet())
        {
            final Series dailyHigh = using(adopt(daily.high()));
            final Series dailyLow = using(adopt(daily.low()));
            final Series dailyClose = using(adopt(daily.close()));
            final Series dailyTime = using(adopt(daily.timescale()));
            final Series intradayTime = using(timescale());

            @Override
            public void update(int i, BarState state)
            {
                data.highPrice = dailyHigh.at(i);
                data.lowPrice = dailyLow.at(i);
                data.closePrice = dailyClose.at(i);
                data.startIndex = (long) dailyTime.at(i);
                data.endIndex = (long) intradayTime.at(i);
            }
        });
    }
}
