'use strict';

function _typeof(o) { "@babel/helpers - typeof"; return _typeof = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (o) { return typeof o; } : function (o) { return o && "function" == typeof Symbol && o.constructor === Symbol && o !== Symbol.prototype ? "symbol" : typeof o; }, _typeof(o); }
function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }
function _defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, _toPropertyKey(descriptor.key), descriptor); } }
function _createClass(Constructor, protoProps, staticProps) { if (protoProps) _defineProperties(Constructor.prototype, protoProps); if (staticProps) _defineProperties(Constructor, staticProps); Object.defineProperty(Constructor, "prototype", { writable: false }); return Constructor; }
function _toPropertyKey(arg) { var key = _toPrimitive(arg, "string"); return _typeof(key) === "symbol" ? key : String(key); }
function _toPrimitive(input, hint) { if (_typeof(input) !== "object" || input === null) return input; var prim = input[Symbol.toPrimitive]; if (prim !== undefined) { var res = prim.call(input, hint || "default"); if (_typeof(res) !== "object") return res; throw new TypeError("@@toPrimitive must return a primitive value."); } return (hint === "string" ? String : Number)(input); }
function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function"); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, writable: true, configurable: true } }); Object.defineProperty(subClass, "prototype", { writable: false }); if (superClass) _setPrototypeOf(subClass, superClass); }
function _createSuper(Derived) { var hasNativeReflectConstruct = _isNativeReflectConstruct(); return function _createSuperInternal() { var Super = _getPrototypeOf(Derived), result; if (hasNativeReflectConstruct) { var NewTarget = _getPrototypeOf(this).constructor; result = Reflect.construct(Super, arguments, NewTarget); } else { result = Super.apply(this, arguments); } return _possibleConstructorReturn(this, result); }; }
function _possibleConstructorReturn(self, call) { if (call && (_typeof(call) === "object" || typeof call === "function")) { return call; } else if (call !== void 0) { throw new TypeError("Derived constructors may only return object or undefined"); } return _assertThisInitialized(self); }
function _assertThisInitialized(self) { if (self === void 0) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return self; }
function _wrapNativeSuper(Class) { var _cache = typeof Map === "function" ? new Map() : undefined; _wrapNativeSuper = function _wrapNativeSuper(Class) { if (Class === null || !_isNativeFunction(Class)) return Class; if (typeof Class !== "function") { throw new TypeError("Super expression must either be null or a function"); } if (typeof _cache !== "undefined") { if (_cache.has(Class)) return _cache.get(Class); _cache.set(Class, Wrapper); } function Wrapper() { return _construct(Class, arguments, _getPrototypeOf(this).constructor); } Wrapper.prototype = Object.create(Class.prototype, { constructor: { value: Wrapper, enumerable: false, writable: true, configurable: true } }); return _setPrototypeOf(Wrapper, Class); }; return _wrapNativeSuper(Class); }
function _construct(Parent, args, Class) { if (_isNativeReflectConstruct()) { _construct = Reflect.construct.bind(); } else { _construct = function _construct(Parent, args, Class) { var a = [null]; a.push.apply(a, args); var Constructor = Function.bind.apply(Parent, a); var instance = new Constructor(); if (Class) _setPrototypeOf(instance, Class.prototype); return instance; }; } return _construct.apply(null, arguments); }
function _isNativeReflectConstruct() { if (typeof Reflect === "undefined" || !Reflect.construct) return false; if (Reflect.construct.sham) return false; if (typeof Proxy === "function") return true; try { Boolean.prototype.valueOf.call(Reflect.construct(Boolean, [], function () {})); return true; } catch (e) { return false; } }
function _isNativeFunction(fn) { try { return Function.toString.call(fn).indexOf("[native code]") !== -1; } catch (e) { return typeof fn === "function"; } }
function _setPrototypeOf(o, p) { _setPrototypeOf = Object.setPrototypeOf ? Object.setPrototypeOf.bind() : function _setPrototypeOf(o, p) { o.__proto__ = p; return o; }; return _setPrototypeOf(o, p); }
function _getPrototypeOf(o) { _getPrototypeOf = Object.setPrototypeOf ? Object.getPrototypeOf.bind() : function _getPrototypeOf(o) { return o.__proto__ || Object.getPrototypeOf(o); }; return _getPrototypeOf(o); }
customElements.define('compodoc-menu', /*#__PURE__*/function (_HTMLElement) {
  _inherits(_class, _HTMLElement);
  var _super = _createSuper(_class);
  function _class() {
    var _this;
    _classCallCheck(this, _class);
    _this = _super.call(this);
    _this.isNormalMode = _this.getAttribute('mode') === 'normal';
    return _this;
  }
  _createClass(_class, [{
    key: "connectedCallback",
    value: function connectedCallback() {
      this.render(this.isNormalMode);
    }
  }, {
    key: "render",
    value: function render(isNormalMode) {
      var tp = lithtml.html("\n        <nav>\n            <ul class=\"list\">\n                <li class=\"title\">\n                    <a href=\"index.html\" data-type=\"index-link\">fleet-brother-web-client documentation</a>\n                </li>\n\n                <li class=\"divider\"></li>\n                ".concat(isNormalMode ? "<div id=\"book-search-input\" role=\"search\"><input type=\"text\" placeholder=\"Type to search\"></div>" : '', "\n                <li class=\"chapter\">\n                    <a data-type=\"chapter-link\" href=\"index.html\"><span class=\"icon ion-ios-home\"></span>Getting started</a>\n                    <ul class=\"links\">\n                        <li class=\"link\">\n                            <a href=\"overview.html\" data-type=\"chapter-link\">\n                                <span class=\"icon ion-ios-keypad\"></span>Overview\n                            </a>\n                        </li>\n                        <li class=\"link\">\n                            <a href=\"index.html\" data-type=\"chapter-link\">\n                                <span class=\"icon ion-ios-paper\"></span>README\n                            </a>\n                        </li>\n                                <li class=\"link\">\n                                    <a href=\"dependencies.html\" data-type=\"chapter-link\">\n                                        <span class=\"icon ion-ios-list\"></span>Dependencies\n                                    </a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"properties.html\" data-type=\"chapter-link\">\n                                        <span class=\"icon ion-ios-apps\"></span>Properties\n                                    </a>\n                                </li>\n                    </ul>\n                </li>\n                    <li class=\"chapter modules\">\n                        <a data-type=\"chapter-link\" href=\"modules.html\">\n                            <div class=\"menu-toggler linked\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#modules-links"' : 'data-bs-target="#xs-modules-links"', ">\n                                <span class=\"icon ion-ios-archive\"></span>\n                                <span class=\"link-name\">Modules</span>\n                                <span class=\"icon ion-ios-arrow-down\"></span>\n                            </div>\n                        </a>\n                        <ul class=\"links collapse \" ").concat(isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"', ">\n                            <li class=\"link\">\n                                <a href=\"modules/AppModule.html\" data-type=\"entity-link\" >AppModule</a>\n                                    <li class=\"chapter inner\">\n                                        <div class=\"simple menu-toggler\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#components-links-module-AppModule-313ac660be1fcec46a2d88e70939c57d5f7a28460a944f7be10e4bb34888000029679c79623c4fa30de053bac5fdf5292c73e9c80496d57230984daf3cee62a2"' : 'data-bs-target="#xs-components-links-module-AppModule-313ac660be1fcec46a2d88e70939c57d5f7a28460a944f7be10e4bb34888000029679c79623c4fa30de053bac5fdf5292c73e9c80496d57230984daf3cee62a2"', ">\n                                            <span class=\"icon ion-md-cog\"></span>\n                                            <span>Components</span>\n                                            <span class=\"icon ion-ios-arrow-down\"></span>\n                                        </div>\n                                        <ul class=\"links collapse\" ").concat(isNormalMode ? 'id="components-links-module-AppModule-313ac660be1fcec46a2d88e70939c57d5f7a28460a944f7be10e4bb34888000029679c79623c4fa30de053bac5fdf5292c73e9c80496d57230984daf3cee62a2"' : 'id="xs-components-links-module-AppModule-313ac660be1fcec46a2d88e70939c57d5f7a28460a944f7be10e4bb34888000029679c79623c4fa30de053bac5fdf5292c73e9c80496d57230984daf3cee62a2"', ">\n                                            <li class=\"link\">\n                                                <a href=\"components/AlertCreationComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >AlertCreationComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/AlertOverviewComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >AlertOverviewComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/AlertsManagerComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >AlertsManagerComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/AppComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >AppComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/CarAssigmentPageComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >CarAssigmentPageComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/CarFleetAssigmentComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >CarFleetAssigmentComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/CarOverviewComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >CarOverviewComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/CarReportingPageComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >CarReportingPageComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/FieldCreationComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >FieldCreationComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/FieldOverviewComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >FieldOverviewComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/FleetManagementCreateComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >FleetManagementCreateComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/FleetManagementPageComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >FleetManagementPageComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/FleetOverviewComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >FleetOverviewComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/FleetViewPageComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >FleetViewPageComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/LoginPageComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >LoginPageComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/MessageDialogComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >MessageDialogComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/ReportComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >ReportComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/SchemaCarAssigmentComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >SchemaCarAssigmentComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/SchemaCreateComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >SchemaCreateComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/SchemaManagementPageComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >SchemaManagementPageComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/SchemaOverviewComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >SchemaOverviewComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/SidebarComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >SidebarComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/UserCreateComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >UserCreateComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/UserCreationPageComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >UserCreationPageComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/UserOverviewComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >UserOverviewComponent</a>\n                                            </li>\n                                            <li class=\"link\">\n                                                <a href=\"components/WelcomePageComponent.html\" data-type=\"entity-link\" data-context=\"sub-entity\" data-context-id=\"modules\" >WelcomePageComponent</a>\n                                            </li>\n                                        </ul>\n                                    </li>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"modules/AppRoutingModule.html\" data-type=\"entity-link\" >AppRoutingModule</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"modules/MaterialModule.html\" data-type=\"entity-link\" >MaterialModule</a>\n                            </li>\n                </ul>\n                </li>\n                    <li class=\"chapter\">\n                        <div class=\"simple menu-toggler\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#classes-links"' : 'data-bs-target="#xs-classes-links"', ">\n                            <span class=\"icon ion-ios-paper\"></span>\n                            <span>Classes</span>\n                            <span class=\"icon ion-ios-arrow-down\"></span>\n                        </div>\n                        <ul class=\"links collapse \" ").concat(isNormalMode ? 'id="classes-links"' : 'id="xs-classes-links"', ">\n                            <li class=\"link\">\n                                <a href=\"classes/CheckboxModel.html\" data-type=\"entity-link\" >CheckboxModel</a>\n                            </li>\n                        </ul>\n                    </li>\n                        <li class=\"chapter\">\n                            <div class=\"simple menu-toggler\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#injectables-links"' : 'data-bs-target="#xs-injectables-links"', ">\n                                <span class=\"icon ion-md-arrow-round-down\"></span>\n                                <span>Injectables</span>\n                                <span class=\"icon ion-ios-arrow-down\"></span>\n                            </div>\n                            <ul class=\"links collapse \" ").concat(isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"', ">\n                                <li class=\"link\">\n                                    <a href=\"injectables/AlertsManagementService.html\" data-type=\"entity-link\" >AlertsManagementService</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/CarReportingService.html\" data-type=\"entity-link\" >CarReportingService</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/FirebaseService.html\" data-type=\"entity-link\" >FirebaseService</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/FleetManagementService.html\" data-type=\"entity-link\" >FleetManagementService</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/LoadingService.html\" data-type=\"entity-link\" >LoadingService</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/LoginService.html\" data-type=\"entity-link\" >LoginService</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/MessageDialogService.html\" data-type=\"entity-link\" >MessageDialogService</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/SchemaManagementService.html\" data-type=\"entity-link\" >SchemaManagementService</a>\n                                </li>\n                                <li class=\"link\">\n                                    <a href=\"injectables/UserManagementService.html\" data-type=\"entity-link\" >UserManagementService</a>\n                                </li>\n                            </ul>\n                        </li>\n                    <li class=\"chapter\">\n                        <div class=\"simple menu-toggler\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#interceptors-links"' : 'data-bs-target="#xs-interceptors-links"', ">\n                            <span class=\"icon ion-ios-swap\"></span>\n                            <span>Interceptors</span>\n                            <span class=\"icon ion-ios-arrow-down\"></span>\n                        </div>\n                        <ul class=\"links collapse \" ").concat(isNormalMode ? 'id="interceptors-links"' : 'id="xs-interceptors-links"', ">\n                            <li class=\"link\">\n                                <a href=\"interceptors/AuthInterceptor.html\" data-type=\"entity-link\" >AuthInterceptor</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interceptors/LoadingInterceptor.html\" data-type=\"entity-link\" >LoadingInterceptor</a>\n                            </li>\n                        </ul>\n                    </li>\n                    <li class=\"chapter\">\n                        <div class=\"simple menu-toggler\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#guards-links"' : 'data-bs-target="#xs-guards-links"', ">\n                            <span class=\"icon ion-ios-lock\"></span>\n                            <span>Guards</span>\n                            <span class=\"icon ion-ios-arrow-down\"></span>\n                        </div>\n                        <ul class=\"links collapse \" ").concat(isNormalMode ? 'id="guards-links"' : 'id="xs-guards-links"', ">\n                            <li class=\"link\">\n                                <a href=\"guards/AdminGuard.html\" data-type=\"entity-link\" >AdminGuard</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"guards/AuthGuard.html\" data-type=\"entity-link\" >AuthGuard</a>\n                            </li>\n                        </ul>\n                    </li>\n                    <li class=\"chapter\">\n                        <div class=\"simple menu-toggler\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#interfaces-links"' : 'data-bs-target="#xs-interfaces-links"', ">\n                            <span class=\"icon ion-md-information-circle-outline\"></span>\n                            <span>Interfaces</span>\n                            <span class=\"icon ion-ios-arrow-down\"></span>\n                        </div>\n                        <ul class=\"links collapse \" ").concat(isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"', ">\n                            <li class=\"link\">\n                                <a href=\"interfaces/AlertBase.html\" data-type=\"entity-link\" >AlertBase</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/AlertCreationRequestBodyBase.html\" data-type=\"entity-link\" >AlertCreationRequestBodyBase</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/Car.html\" data-type=\"entity-link\" >Car</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/ExistsValueAlert.html\" data-type=\"entity-link\" >ExistsValueAlert</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/ExistsValueAlertCreationRequestBody.html\" data-type=\"entity-link\" >ExistsValueAlertCreationRequestBody</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/Field.html\" data-type=\"entity-link\" >Field</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/FieldCreationRequestBody.html\" data-type=\"entity-link\" >FieldCreationRequestBody</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/Fleet.html\" data-type=\"entity-link\" >Fleet</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/FleetCreationRequestBody.html\" data-type=\"entity-link\" >FleetCreationRequestBody</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/ForbiddenValueAlert.html\" data-type=\"entity-link\" >ForbiddenValueAlert</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/ForbiddenValueAlertCreationRequestBody.html\" data-type=\"entity-link\" >ForbiddenValueAlertCreationRequestBody</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/Login.html\" data-type=\"entity-link\" >Login</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/MaxValueAlert.html\" data-type=\"entity-link\" >MaxValueAlert</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/MaxValueAlertCreationRequestBody.html\" data-type=\"entity-link\" >MaxValueAlertCreationRequestBody</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/Measurement.html\" data-type=\"entity-link\" >Measurement</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/MinValueAlert.html\" data-type=\"entity-link\" >MinValueAlert</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/MinValueAlertCreationRequestBody.html\" data-type=\"entity-link\" >MinValueAlertCreationRequestBody</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/Schema.html\" data-type=\"entity-link\" >Schema</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/SchemaCarAssigmentOutput.html\" data-type=\"entity-link\" >SchemaCarAssigmentOutput</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/SchemaCreationRequestBody.html\" data-type=\"entity-link\" >SchemaCreationRequestBody</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/User.html\" data-type=\"entity-link\" >User</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"interfaces/UserCreationRequestBody.html\" data-type=\"entity-link\" >UserCreationRequestBody</a>\n                            </li>\n                        </ul>\n                    </li>\n                    <li class=\"chapter\">\n                        <div class=\"simple menu-toggler\" data-bs-toggle=\"collapse\" ").concat(isNormalMode ? 'data-bs-target="#miscellaneous-links"' : 'data-bs-target="#xs-miscellaneous-links"', ">\n                            <span class=\"icon ion-ios-cube\"></span>\n                            <span>Miscellaneous</span>\n                            <span class=\"icon ion-ios-arrow-down\"></span>\n                        </div>\n                        <ul class=\"links collapse \" ").concat(isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"', ">\n                            <li class=\"link\">\n                                <a href=\"miscellaneous/enumerations.html\" data-type=\"entity-link\">Enums</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"miscellaneous/functions.html\" data-type=\"entity-link\">Functions</a>\n                            </li>\n                            <li class=\"link\">\n                                <a href=\"miscellaneous/typealiases.html\" data-type=\"entity-link\">Type aliases</a>\n                            </li>\n                        </ul>\n                    </li>\n                        <li class=\"chapter\">\n                            <a data-type=\"chapter-link\" href=\"routes.html\"><span class=\"icon ion-ios-git-branch\"></span>Routes</a>\n                        </li>\n                    <li class=\"chapter\">\n                        <a data-type=\"chapter-link\" href=\"coverage.html\"><span class=\"icon ion-ios-stats\"></span>Documentation coverage</a>\n                    </li>\n                    <li class=\"divider\"></li>\n                    <li class=\"copyright\">\n                        Documentation generated using <a href=\"https://compodoc.app/\" target=\"_blank\" rel=\"noopener noreferrer\">\n                            <img data-src=\"images/compodoc-vectorise.png\" class=\"img-responsive\" data-type=\"compodoc-logo\">\n                        </a>\n                    </li>\n            </ul>\n        </nav>\n        "));
      this.innerHTML = tp.strings;
    }
  }]);
  return _class;
}( /*#__PURE__*/_wrapNativeSuper(HTMLElement)));