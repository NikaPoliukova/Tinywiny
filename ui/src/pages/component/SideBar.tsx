import * as React from 'react';
import {useEffect, useState} from "react";
import {TypeProduct} from "../../model/TypeProduct";
import TypeProductService from "../../services/TypeProductService";


export const Sidebar: React.FC = (): JSX.Element => {
    const [types, setTypes] = useState<Array<TypeProduct>>([])
    useEffect(() => {
        TypeProductService.findAllTypes()
            .then(response => setTypes(response));

    }, []);
 return (

        <aside >
            <div >
                <div >
                    <span>
            Types of products
                    </span>
                </div>
                <div>
                    <a href="/products/type/toys" className="w3-bar-item w3-button w3-border-bottom" >Toys</a>
                </div>
                    <div>
                    <a href="/products/type/wings" className="w3-bar-item w3-button w3-border-bottom">Wings</a>
                    </div>
                <div>
                    <a href="/products/type/mobiles" className="w3-bar-item w3-button">Mobiles</a>
                </div>
                <div>
                    <a href="/products/type/decorations" className="w3-bar-item w3-button">Decorations</a>
                </div>

            </div>
        </aside>

    );
};



