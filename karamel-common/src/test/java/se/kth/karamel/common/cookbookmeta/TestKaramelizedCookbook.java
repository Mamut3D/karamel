/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.karamel.common.cookbookmeta;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import se.kth.karamel.common.util.IoUtils;
import se.kth.karamel.common.util.Settings;
import se.kth.karamel.common.exception.CookbookUrlException;
import se.kth.karamel.common.exception.MetadataParseException;
import se.kth.karamel.common.exception.RecipeParseException;
import se.kth.karamel.common.exception.ValidationException;

/**
 *
 * @author kamal
 */
public class TestKaramelizedCookbook {

  @Test
  public void testLoadingClasspathCookbook() throws ValidationException {
    try {
      Settings.CB_CLASSPATH_MODE = true;
      KaramelizedCookbook cb = new KaramelizedCookbook("testorg/testrepo/tree/master/cookbooks/hopshadoop/hopsworks-chef", false);
    } catch (CookbookUrlException | MetadataParseException e) {
      Assert.fail();
    }
  }

  @Test
  public void testMetadata() throws MetadataParseException, IOException {
    String file = Resources.toString(Resources.getResource("se/kth/karamel/cookbook/metadata/metadata.rb"),
        Charsets.UTF_8);
    MetadataRb metadatarb = MetadataParser.parse(file);
    List<Recipe> recipes = metadatarb.getRecipes();
    assertEquals(recipes.size(), 2);
    Recipe r1 = recipes.get(0);
    Recipe r2 = recipes.get(1);
    assertEquals(r1.getName(), "hopsworks::install");
    Set<String> l1 = r1.getLinks();
    assertEquals(l1.size(), 0);
    assertEquals(r2.getName(), "hopsworks::default");
    Set<String> l2 = r2.getLinks();
    assertEquals(l2.size(), 2);
    assertEquals(l2.toArray()[0], "Click {here,https://%host%:8181/hop-dashboard} to launch hopsworks in your browser");
    assertEquals(l2.toArray()[1], "Visit Karamel {here,www.karamel.io}");
  }

  @Test
  public void testLoadDependencies() throws CookbookUrlException, IOException {
    Settings.CB_CLASSPATH_MODE = true;
    List<String> list = IoUtils.readLinesFromClasspath("testgithub/testorg/testrepo/master/cookbooks/hopshadoop/hopsworks-chef/Berksfile");
    Berksfile berksfile = new Berksfile(list);
  }

  @Test
  public void testParseRecipes() throws CookbookUrlException, IOException {
    try {
      Settings.CB_CLASSPATH_MODE = true;
      String recipe = Resources.toString(Resources.getResource(
          "testgithub/testorg/testrepo/master/cookbooks/hopshadoop/hopsworks-chef/recipes/experiment.rb"), Charsets.UTF_8);
      ExperimentRecipe er = ExperimentRecipeParser.parse("experiment", recipe, "config.props", "x=y");
      assertEquals("experiment", er.getRecipeName());
      assertEquals(er.getConfigFileName().isEmpty(), false);
      assertEquals(er.getConfigFileContents().isEmpty(), false);
      assertEquals(er.getScriptContents().isEmpty(), false);
      assertEquals(er.getScriptType(), "bash");
    } catch (RecipeParseException ex) {
      Assert.fail(ex.toString());
    }
    
  }
}
