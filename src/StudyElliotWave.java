/*
 * Copyright © 2013, TradingView, Inc. All rights reserved.
 * www.tradingview.com
 */
import studylib.sdk.*;
import studylib.sdk.studies.ElliotWave;
import studylib.sdk.studies.ElliotWaveContainer;

import java.util.ArrayList;
import java.util.List;

//NOTE: This study has a hardcoded custom properties on the client side (defaults.js)
@StudyDecl(studyId = "ElliottWave", isPriceStudy = true, isLinkedToSeries = true,
           title = "Elliott Wave", shortTitle = "Elliott")
public class StudyElliotWave extends Study
{
    public static class Data extends JsonObj implements JsonObj.Modifiable
    {
        List<ElliotWave> waves = new ArrayList<>();

        @IgnoredField
        boolean modified = true;

        @Override
        public boolean isModified()
        {
            return modified;
        }

        @Override
        public void setModified(boolean modified)
        {
            this.modified = modified;
        }
    }

    @StudyOutputData
    Data data = new Data();

    @Override
    public void init()
    {
        final boolean calcOnlyCompletedBars = true;
        ElliotWaveContainer waveContainer = new ElliotWaveContainer(data.waves, data, timescale());
        newSeries(new ElliotWave(barSet(), waveContainer, calcOnlyCompletedBars));
    }
}
