package com.relation.util;

public class SpiderTest
{
    /**
     * This is our test. It creates a spider (which creates spider legs) and crawls the web.
     * 
     * @param args
     *            - not used
     */
    public static void main(String[] args)
    {
        Spider spider = new Spider();
       // spider.search("http://arstechnica.com/", "computer");
        spider.search("http://econda.de/", "shop");
        //spider.search("http://econda.de/");
        
    }
}
