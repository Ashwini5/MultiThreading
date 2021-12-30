package org.example;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Get set go!" );
        RaceController raceController = new RaceController(Constants.NUMBER_OF_RACERS);
        RaceFeed raceFeed = new RaceFeed(raceController);
    }
}
