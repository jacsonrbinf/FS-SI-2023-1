package br.ufg.inf.application.views;


import br.ufg.inf.application.components.appnav.AppNav;
import br.ufg.inf.application.components.appnav.AppNavItem;
import br.ufg.inf.application.views.about.AboutView;
import br.ufg.inf.application.views.apartamento.ApartamentoListView;
import br.ufg.inf.application.views.apartamento.ApartamentoView;
import br.ufg.inf.application.views.edificio.EdificioListView;
import br.ufg.inf.application.views.morador.MoradorListView;
import br.ufg.inf.application.views.morador.MoradorView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
@Route(value = "")
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Pratica-02");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();
        nav.addItem(new AppNavItem("Morador ", MoradorListView.class, LineAwesomeIcon.USER.create()));
        nav.addItem(new AppNavItem("Apartamento ", ApartamentoListView.class, LineAwesomeIcon.USER.create()));
        nav.addItem(new AppNavItem("Edificio", EdificioListView.class, LineAwesomeIcon.USER.create()));
        nav.addItem(new AppNavItem("About", AboutView.class, LineAwesomeIcon.FILE.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
