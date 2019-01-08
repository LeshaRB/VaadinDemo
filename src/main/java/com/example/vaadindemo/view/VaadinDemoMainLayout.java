package com.example.vaadindemo.view;

import static com.github.appreciated.app.layout.entity.Section.FOOTER;
import static com.github.appreciated.app.layout.entity.Section.HEADER;
import static com.github.appreciated.app.layout.notification.entitiy.Priority.MEDIUM;

import com.example.vaadindemo.component.BehaviourSelector;
import com.github.appreciated.app.layout.behaviour.AppLayout;
import com.github.appreciated.app.layout.behaviour.Behaviour;
import com.github.appreciated.app.layout.builder.AppLayoutBuilder;
import com.github.appreciated.app.layout.component.appbar.AppBarBuilder;
import com.github.appreciated.app.layout.component.appmenu.MenuHeaderComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftClickableComponent;
import com.github.appreciated.app.layout.component.appmenu.left.LeftNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftAppMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.left.builder.LeftSubMenuBuilder;
import com.github.appreciated.app.layout.component.appmenu.top.TopClickableComponent;
import com.github.appreciated.app.layout.component.appmenu.top.TopNavigationComponent;
import com.github.appreciated.app.layout.component.appmenu.top.builder.TopAppMenuBuilder;
import com.github.appreciated.app.layout.entity.DefaultBadgeHolder;
import com.github.appreciated.app.layout.notification.DefaultNotificationHolder;
import com.github.appreciated.app.layout.notification.component.AppBarNotificationButton;
import com.github.appreciated.app.layout.notification.entitiy.DefaultNotification;
import com.github.appreciated.app.layout.notification.entitiy.Priority;
import com.github.appreciated.app.layout.router.AppLayoutRouterLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.page.BodySize;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;

/**
 * The main view contains a button and a template element.
 */

@Push
@BodySize(height = "100vh", width = "100vw")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class VaadinDemoMainLayout extends AppLayoutRouterLayout {

    private DefaultNotificationHolder notificationHolder;
    private DefaultBadgeHolder badgeHolder;
    private Behaviour variant;
    private Thread currentThread;

    @Override
    public AppLayout createAppLayoutInstance() {
        if (variant == null) {
            variant = Behaviour.LEFT_RESPONSIVE;
            notificationHolder = new DefaultNotificationHolder(newStatus -> {/*Do something with it*/});
            badgeHolder = new DefaultBadgeHolder();
        }
        reloadNotifications();

        if (!variant.isTop()) {
            LeftNavigationComponent home = new LeftNavigationComponent("Home", VaadinIcon.HOME.create(),
                    VaadinDemoHome.class);
            LeftNavigationComponent menu = new LeftNavigationComponent("Menu", VaadinIcon.MENU.create(),
                    VaadinDemoMenu.class);

            notificationHolder.bind(home.getBadge());
            badgeHolder.bind(menu.getBadge());

            return createAppLayout(home, menu);
        } else {
            return createAppLayoutTop();
        }
    }

    private AppLayout createAppLayout(LeftNavigationComponent home, LeftNavigationComponent menu) {
        AppBarNotificationButton appBarNotificationButton = new AppBarNotificationButton(VaadinIcon.BELL,
                notificationHolder);
        MenuHeaderComponent menuHeaderComponent = new MenuHeaderComponent("Vaadin Demo", "Version 0.0.1",
                "frontend/images/logo.png");

        LeftClickableComponent behaviourHeader = new LeftClickableComponent("Set Behaviour HEADER",
                VaadinIcon.COG.create(), clickEvent -> openModeSelector(variant));
        LeftClickableComponent behaviourFooter = new LeftClickableComponent("Set Behaviour FOOTER",
                VaadinIcon.COG.create(), clickEvent -> openModeSelector(variant));

        LeftNavigationComponent grid = new LeftNavigationComponent("Grid", VaadinIcon.TABLE.create(),
                VaadinDemoGrid.class);
        LeftNavigationComponent contact = new LeftNavigationComponent("Contact", VaadinIcon.CONNECT.create(),
                VaadinDemoContact.class);
        LeftNavigationComponent more = new LeftNavigationComponent("More", VaadinIcon.COG.create(),
                VaadinDemoMore.class);

        return AppLayoutBuilder
                .get(variant)
                .withTitle("Vaadin Demo")
                .withIcon("frontend/images/logo.png")
                .withAppBar(AppBarBuilder.get().add(appBarNotificationButton).build())
                .withAppMenu(LeftAppMenuBuilder
                        .get()
                        .addToSection(menuHeaderComponent, HEADER)
                        .addToSection(behaviourHeader, HEADER)
                        .add(home)
                        .add(grid)
                        .add(LeftSubMenuBuilder.get("My Submenu", VaadinIcon.PLUS.create()).add(contact).add(more)
                                .build())
                        .add(menu)
                        .addToSection(behaviourFooter, FOOTER)
                        .build())
                .build();
    }

    private AppLayout createAppLayoutTop() {
        AppBarNotificationButton appBarNotificationButton = new AppBarNotificationButton(VaadinIcon.BELL,
                notificationHolder);

        TopClickableComponent behaviour1 = new TopClickableComponent("Set Behaviour 1", VaadinIcon.COG.create(),
                clickEvent -> openModeSelector(variant));
        TopClickableComponent behaviour2 = new TopClickableComponent("Set Behaviour 2", VaadinIcon.COG.create(),
                clickEvent -> openModeSelector(variant));

        TopNavigationComponent home = new TopNavigationComponent("Home", VaadinIcon.HOME.create(),
                VaadinDemoHome.class);
        TopNavigationComponent contact = new TopNavigationComponent("Contact", VaadinIcon.SPLINE_CHART.create(),
                VaadinDemoContact.class);
        TopNavigationComponent more = new TopNavigationComponent("More", VaadinIcon.CONNECT.create(),
                VaadinDemoMore.class);

        return AppLayoutBuilder
                .get(variant)
                .withTitle("Vaadin Demo Top")
                .withAppBar(AppBarBuilder
                        .get()
                        .add(appBarNotificationButton)
                        .build())
                .withAppMenu(TopAppMenuBuilder
                        .get()
                        .addToSection(behaviour1, HEADER)
                        .add(home)
                        .add(contact)
                        .addToSection(behaviour2, FOOTER)
                        .addToSection(more, FOOTER)
                        .build())
                .build();
    }

    private void reloadNotifications() {
        if (currentThread != null && !currentThread.isInterrupted()) {
            currentThread.interrupt();
        }
        badgeHolder.clearCount();
        notificationHolder.clearNotifications();
        currentThread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                for (int i = 0; i < 3; i++) {
                    //Thread.sleep(5000);
                    getUI().ifPresent(ui -> ui.access(() -> {
                        addNotification(MEDIUM);
                        badgeHolder.increase();
                        badgeHolder.increase();
                    }));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        currentThread.start();
    }

    private void addNotification(Priority priority) {
        notificationHolder.addNotification(
                new DefaultNotification("Title" + badgeHolder.getCount(),
                        "Description ..............................................."
                                + badgeHolder.getCount(),
                        priority));
    }

    private void setDrawerVariant(Behaviour variant) {
        this.variant = variant;
        reloadConfiguration();
    }

    private void openModeSelector(Behaviour variant) {
        new BehaviourSelector(variant, this::setDrawerVariant).open();
    }
}
