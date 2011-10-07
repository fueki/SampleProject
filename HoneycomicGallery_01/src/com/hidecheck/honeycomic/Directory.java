package com.hidecheck.honeycomic;


public class Directory {
	private static DirectoryCategory[] mCategories;
	
	public static void initializeDirectory(){
		
		mCategories = new DirectoryCategory[]{
                new DirectoryCategory("Balloons", new DirectoryEntry[] {
                        new DirectoryEntry("Red Balloon", R.drawable.red_balloon),
                        new DirectoryEntry("Green Balloon", R.drawable.green_balloon),
                        new DirectoryEntry("Blue Balloon", R.drawable.blue_balloon)}),
                new DirectoryCategory("Bikes", new DirectoryEntry[] {
                        new DirectoryEntry("Old school huffy", R.drawable.blue_bike),
                        new DirectoryEntry("New Bikes", R.drawable.rainbow_bike),
                        new DirectoryEntry("Chrome Fast", R.drawable.chrome_wheel)}),
                new DirectoryCategory("Androids", new DirectoryEntry[] {
                        new DirectoryEntry("Steampunk Android", R.drawable.punk_droid),
                        new DirectoryEntry("Stargazing Android", R.drawable.stargazer_droid),
                        new DirectoryEntry("Big Android", R.drawable.big_droid) }),
                new DirectoryCategory("Pastries", new DirectoryEntry[] {
                        new DirectoryEntry("Cupcake", R.drawable.cupcake),
                        new DirectoryEntry("Donut", R.drawable.donut),
                        new DirectoryEntry("Eclair", R.drawable.eclair),
                        new DirectoryEntry("Froyo", R.drawable.froyo), }),
               };
	}

	public static int getmCategoryCount() {
		return mCategories.length;
	}
	
	public static DirectoryCategory getCategories(int i) {
		return mCategories[i];
	}
	
}
