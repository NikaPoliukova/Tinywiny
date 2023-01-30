import React from 'react';
import {Link} from "react-router-dom";



const RegistryPage = () => {
    return (
        <div>

            <h1>REgistration Page</h1>
        <p>
        Or <Link to="/login">login</Link>
        </p>
        </div>
);
};

export default RegistryPage;