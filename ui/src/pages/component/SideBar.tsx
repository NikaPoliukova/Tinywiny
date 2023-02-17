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
        <aside className="w-300 h-full">
            <div className="overflow-y-auto h-screen py-4 px-3 bg-gray-50  dark:bg-gray-800">
                <div className="flex items-center pl-2.5 mb-5">
                    <span className="self-center text-xl font-semibold whitespace-nowrap dark:text-white">
            Types of products
                    </span>
                </div>

                <div className="w5-sidebar w3-bar-block" >
                    <a href="/products/toys" className="w3-bar-item w3-button w3-border-bottom" >Toys</a>
                </div>
                    <div className="w3-sidebar w3-bar-block">
                    <a href="/products/wings" className="w3-bar-item w3-button w3-border-bottom">Wings</a>
                    </div>
                <div className="w3-sidebar w3-bar-block">
                    <a href="/products/mobiles" className="w3-bar-item w3-button">Mobiles</a>
                </div>
                <div className="w3-sidebar w3-bar-block">
                    <a href="/products/decorations" className="w3-bar-item w3-button">Decorations</a>
                </div>

            </div>
        </aside>
    );
};



