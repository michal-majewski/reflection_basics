package com.mmajewski.experimental.fields.intro;

class Movie extends Product {
    public static final double MINIMUM_PRICE = 10.99;

    private boolean isReleased;
    private Category category;
    private double actualPrice;

    protected Movie(String name, int year, double price, boolean isReleased, Category category) {
        super(name, year);
        this.isReleased = isReleased;
        this.category = category;
        this.actualPrice = Math.max(price, MINIMUM_PRICE);
    }

    /**
     * Nested class - for the example with synthetic fields
     */
    class MovieStats {
        private double timeWatched;

        public MovieStats(double timeWatched) {
            this.timeWatched = timeWatched;
        }

        double getRevenue() {
            return timeWatched * actualPrice;
        }
    }
}
