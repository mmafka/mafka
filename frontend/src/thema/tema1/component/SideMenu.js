import React from 'react';
import './index.css';

class SideMenu extends React.Component {
    
    constructor(props)
    {
        super(props);
        if(localStorage.getItem("token")==null)
            window.location="/login"


    }
    render(props) {
        return (
            <aside className="main-sidebar sidebar-dark-primary elevation-4">
                <a href="/anasayfa" className="brand-link">
                    <img src="thema/tema1/dist/img/AdminLTELogo.png" alt="Okul Soru Yazılımı"
                         className="brand-image img-circle elevation-3"/>
                    <span className="brand-text font-weight-light">Admin</span>
                </a>

                <div className="sidebar">

                    <nav className="mt-2">
                        <ul className="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu"
                            data-accordion="false">
                            <li className="nav-item">
                                <a href="/home" className={this.props.home + " nav-link "+this.showHome}>
                                    <i className="nav-icon fas fa-home"/>
                                    <p>
                                        Home
                                    </p>
                                </a>
                            </li>
                            <li className="nav-item">
                                <a href="/clusters"  className={this.props.clusters + " nav-link "+this.showClusters}>
                                    <i className="nav-icon fas  fa-book"/>
                                    <p>
                                        Clusters
                                    </p>
                                </a>
                            </li>

                            <li className="nav-item">
                                <a href="/logout"  className={" nav-link"}>
                                    <i className="nav-icon fas  fa-sign-out-alt"/>
                                    <p>
                                        Çıkış
                                    </p>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </aside>
        );
    }
}

export default SideMenu;
