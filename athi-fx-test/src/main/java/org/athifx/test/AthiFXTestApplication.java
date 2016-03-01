package org.athifx.test;

import org.athifx.gui.AthiFXApplication;
import org.athifx.gui.menu.group.Groups;
import org.athifx.gui.menu.item.Items;
import org.athifx.test.test_annotations.AnnotationOne;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Athi
 */
public class AthiFXTestApplication extends AthiFXApplication<AthiFXTestApplication.TestGroup, AthiFXTestApplication.TestItem> {

    @Inject
    @Any
    private Instance<TestInterface> instance;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void beforeLaunch() {
        instance.iterator().forEachRemaining(TestInterface::test);
        System.out.println("####");
        instance.select(TestInterfaceImplOne.class.getAnnotations()).iterator().forEachRemaining(TestInterface::test);
        System.out.println("####");
        instance.select(TestInterfaceImplOne.class, TestInterfaceImplOne.class.getAnnotations()).iterator().forEachRemaining(TestInterface::test);
        System.out.println("####--");
        instance.select(TestInterfaceImplTwo.class, TestInterfaceImplOne.class.getAnnotations()).iterator().forEachRemaining(TestInterface::test);
        System.out.println("####--22222");
        instance.select(new TypeLiteral<TestInterfaceImplOne>() {}, TestInterfaceImplOne.class.getAnnotations()).iterator().forEachRemaining(TestInterface::test);
    }

    @Override
    public List<TestGroup> getGroups() {
        return Arrays.asList(TestGroup.values());
    }

    @Override
    public List<TestItem> getItems() {
        return Arrays.asList(TestItem.values());
    }

    enum TestGroup implements Groups {
        GROUP_ONE(1, "GROUP_ONE");

        private long id;
        private String caption;

        TestGroup(long id, String caption) {
            this.id = id;
            this.caption = caption;
        }

        public long id() {
            return id;
        }

        public String caption() {
            return caption;
        }
    }

    enum TestItem implements Items<TestGroup> {
        ITEM_ONE(1, "ITEM_ONE", TestGroup.GROUP_ONE);

        private long id;
        private String caption;
        private TestGroup group;

        TestItem(long id, String caption, TestGroup group) {
            this.id = id;
            this.caption = caption;
            this.group = group;
        }

        public long id() {
            return id;
        }

        public String caption() {
            return caption;
        }

        public TestGroup group() {
            return group;
        }
    }

}