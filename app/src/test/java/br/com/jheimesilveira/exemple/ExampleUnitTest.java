package br.com.jheimesilveira.exemple;

import junit.framework.Assert;

import org.jetbrains.annotations.TestOnly;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
    @TestOnly
    public void addition_isCorrect()
    {
        Assert.assertEquals(4, 2 + 2);
    }
}