import React from 'react';

function PageContext({children}) {

    return (
        <div className="content-wrapper">
            <section className="content">
                <div className="container-fluid">{children}</div>
            </section>
        </div>
);
}

export default PageContext;
