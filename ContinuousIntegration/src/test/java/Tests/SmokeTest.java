package Tests;


import org.testng.annotations.Test;

import Base.BaseClass;
import Pages.ResultPage;
import Pages.SearchPage;

public class SmokeTest extends BaseClass {

SearchPage search=new SearchPage();
ResultPage result=new ResultPage();

	
@Test
public void searchingTest() {
	test=extent.createTest("searchingTest");
	
	search.searchResult();
	result.results();
	
}

	
}
