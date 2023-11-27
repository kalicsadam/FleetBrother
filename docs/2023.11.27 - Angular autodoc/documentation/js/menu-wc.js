'use strict';

customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">fleet-brother-web-client documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                                <li class="link">
                                    <a href="properties.html" data-type="chapter-link">
                                        <span class="icon ion-ios-apps"></span>Properties
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-bs-toggle="collapse" ${ isNormalMode ?
                                'data-bs-target="#modules-links"' : 'data-bs-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link" >AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ?
                                            'data-bs-target="#components-links-module-AppModule-313ac660be1fcec46a2d88e70939c57d5f7a28460a944f7be10e4bb34888000029679c79623c4fa30de053bac5fdf5292c73e9c80496d57230984daf3cee62a2"' : 'data-bs-target="#xs-components-links-module-AppModule-313ac660be1fcec46a2d88e70939c57d5f7a28460a944f7be10e4bb34888000029679c79623c4fa30de053bac5fdf5292c73e9c80496d57230984daf3cee62a2"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-313ac660be1fcec46a2d88e70939c57d5f7a28460a944f7be10e4bb34888000029679c79623c4fa30de053bac5fdf5292c73e9c80496d57230984daf3cee62a2"' :
                                            'id="xs-components-links-module-AppModule-313ac660be1fcec46a2d88e70939c57d5f7a28460a944f7be10e4bb34888000029679c79623c4fa30de053bac5fdf5292c73e9c80496d57230984daf3cee62a2"' }>
                                            <li class="link">
                                                <a href="components/AlertCreationComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AlertCreationComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AlertOverviewComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AlertOverviewComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AlertsManagerComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AlertsManagerComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/AppComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CarAssigmentPageComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CarAssigmentPageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CarFleetAssigmentComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CarFleetAssigmentComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CarOverviewComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CarOverviewComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/CarReportingPageComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >CarReportingPageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/FieldCreationComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >FieldCreationComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/FieldOverviewComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >FieldOverviewComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/FleetManagementCreateComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >FleetManagementCreateComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/FleetManagementPageComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >FleetManagementPageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/FleetOverviewComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >FleetOverviewComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/FleetViewPageComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >FleetViewPageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/LoginPageComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >LoginPageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/MessageDialogComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >MessageDialogComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/ReportComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >ReportComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SchemaCarAssigmentComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SchemaCarAssigmentComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SchemaCreateComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SchemaCreateComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SchemaManagementPageComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SchemaManagementPageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SchemaOverviewComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SchemaOverviewComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/SidebarComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >SidebarComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/UserCreateComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UserCreateComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/UserCreationPageComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UserCreationPageComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/UserOverviewComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >UserOverviewComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/WelcomePageComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >WelcomePageComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                            <li class="link">
                                <a href="modules/AppRoutingModule.html" data-type="entity-link" >AppRoutingModule</a>
                            </li>
                            <li class="link">
                                <a href="modules/MaterialModule.html" data-type="entity-link" >MaterialModule</a>
                            </li>
                </ul>
                </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#classes-links"' :
                            'data-bs-target="#xs-classes-links"' }>
                            <span class="icon ion-ios-paper"></span>
                            <span>Classes</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="classes-links"' : 'id="xs-classes-links"' }>
                            <li class="link">
                                <a href="classes/CheckboxModel.html" data-type="entity-link" >CheckboxModel</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#injectables-links"' :
                                'data-bs-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/AlertsManagementService.html" data-type="entity-link" >AlertsManagementService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/CarReportingService.html" data-type="entity-link" >CarReportingService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/FirebaseService.html" data-type="entity-link" >FirebaseService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/FleetManagementService.html" data-type="entity-link" >FleetManagementService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/LoadingService.html" data-type="entity-link" >LoadingService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/LoginService.html" data-type="entity-link" >LoginService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/MessageDialogService.html" data-type="entity-link" >MessageDialogService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/SchemaManagementService.html" data-type="entity-link" >SchemaManagementService</a>
                                </li>
                                <li class="link">
                                    <a href="injectables/UserManagementService.html" data-type="entity-link" >UserManagementService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#interceptors-links"' :
                            'data-bs-target="#xs-interceptors-links"' }>
                            <span class="icon ion-ios-swap"></span>
                            <span>Interceptors</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="interceptors-links"' : 'id="xs-interceptors-links"' }>
                            <li class="link">
                                <a href="interceptors/AuthInterceptor.html" data-type="entity-link" >AuthInterceptor</a>
                            </li>
                            <li class="link">
                                <a href="interceptors/LoadingInterceptor.html" data-type="entity-link" >LoadingInterceptor</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#guards-links"' :
                            'data-bs-target="#xs-guards-links"' }>
                            <span class="icon ion-ios-lock"></span>
                            <span>Guards</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="guards-links"' : 'id="xs-guards-links"' }>
                            <li class="link">
                                <a href="guards/AdminGuard.html" data-type="entity-link" >AdminGuard</a>
                            </li>
                            <li class="link">
                                <a href="guards/AuthGuard.html" data-type="entity-link" >AuthGuard</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#interfaces-links"' :
                            'data-bs-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/AlertBase.html" data-type="entity-link" >AlertBase</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/AlertCreationRequestBodyBase.html" data-type="entity-link" >AlertCreationRequestBodyBase</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Car.html" data-type="entity-link" >Car</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/ExistsValueAlert.html" data-type="entity-link" >ExistsValueAlert</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/ExistsValueAlertCreationRequestBody.html" data-type="entity-link" >ExistsValueAlertCreationRequestBody</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Field.html" data-type="entity-link" >Field</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/FieldCreationRequestBody.html" data-type="entity-link" >FieldCreationRequestBody</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Fleet.html" data-type="entity-link" >Fleet</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/FleetCreationRequestBody.html" data-type="entity-link" >FleetCreationRequestBody</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/ForbiddenValueAlert.html" data-type="entity-link" >ForbiddenValueAlert</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/ForbiddenValueAlertCreationRequestBody.html" data-type="entity-link" >ForbiddenValueAlertCreationRequestBody</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Login.html" data-type="entity-link" >Login</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/MaxValueAlert.html" data-type="entity-link" >MaxValueAlert</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/MaxValueAlertCreationRequestBody.html" data-type="entity-link" >MaxValueAlertCreationRequestBody</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Measurement.html" data-type="entity-link" >Measurement</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/MinValueAlert.html" data-type="entity-link" >MinValueAlert</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/MinValueAlertCreationRequestBody.html" data-type="entity-link" >MinValueAlertCreationRequestBody</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/Schema.html" data-type="entity-link" >Schema</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/SchemaCarAssigmentOutput.html" data-type="entity-link" >SchemaCarAssigmentOutput</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/SchemaCreationRequestBody.html" data-type="entity-link" >SchemaCreationRequestBody</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/User.html" data-type="entity-link" >User</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/UserCreationRequestBody.html" data-type="entity-link" >UserCreationRequestBody</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-bs-toggle="collapse" ${ isNormalMode ? 'data-bs-target="#miscellaneous-links"'
                            : 'data-bs-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/enumerations.html" data-type="entity-link">Enums</a>
                            </li>
                            <li class="link">
                                <a href="miscellaneous/functions.html" data-type="entity-link">Functions</a>
                            </li>
                            <li class="link">
                                <a href="miscellaneous/typealiases.html" data-type="entity-link">Type aliases</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <a data-type="chapter-link" href="routes.html"><span class="icon ion-ios-git-branch"></span>Routes</a>
                        </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank" rel="noopener noreferrer">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});