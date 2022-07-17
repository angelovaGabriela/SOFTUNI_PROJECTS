package p06_TirePressureMonitoringSystem;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;

public class AlarmTest {

    @Test
    public void testAlarmTurnsOnForLowPressure() {
        Sensor  fakeSensorForLowPressure = Mockito.mock(Sensor.class);

        when(fakeSensorForLowPressure.popNextPressurePsiValue()).thenReturn(12.0);

        Alarm alarm = new Alarm(fakeSensorForLowPressure);
        alarm.check();
        Assert.assertTrue(alarm.getAlarmOn());
    }
}
