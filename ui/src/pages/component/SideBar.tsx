import {Link} from "react-router-dom";


const Sidebar: React.FC = (): JSX.Element => {


    return (
        <aside className="w-300 h-full">
            <div className="overflow-y-auto h-screen py-4 px-3 bg-gray-50  dark:bg-gray-800">
                <div className="flex items-center pl-2.5 mb-5">
                    <span className="self-center text-xl font-semibold whitespace-nowrap dark:text-white">
            Types of products
                    </span>
                </div>

                <div className="w3-sidebar w3-bar-block">
                    <a href="#" className="w3-bar-item w3-button w3-border-bottom">Радуги</a>
                </div>
                    <div className="w3-sidebar w3-bar-block">
                    <a href="#" className="w3-bar-item w3-button w3-border-bottom">Игрушки</a>
                    </div>
                <div className="w3-sidebar w3-bar-block">
                    <a href="#" className="w3-bar-item w3-button">Декор</a>
                </div>
                <div className="w3-sidebar w3-bar-block">
                    <a href="#" className="w3-bar-item w3-button">Мобили</a>
                </div>

            </div>
        </aside>
    );
};

export default Sidebar;