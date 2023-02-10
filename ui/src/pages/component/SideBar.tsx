import {Link} from "react-router-dom";


const Sidebar: React.FC = (): JSX.Element => {


    return (
        <aside className="w-64 h-full">
            <div className="overflow-y-auto h-screen py-4 px-3 bg-gray-50  dark:bg-gray-800">
                <div className="flex items-center pl-2.5 mb-5">
                    <span className="self-center text-xl font-semibold whitespace-nowrap dark:text-white">
            Products
                    </span>
                </div>
                <ul className="space-y-2">
                    <li>
                        <Link to={"/"}>
                            <span className="ml-3">Радуги </span>

                        </Link >
                    </li>
                    <li>
                        <div>
                            <span className="flex-1 ml-3 whitespace-nowrap"> Игрушки  </span>
                        </div>
                    </li>
                    <li>
                        <div>
                            <span className="flex-1 ml-3 whitespace-nowrap"> Мобили  </span>
                        </div>
                    </li>
                    <li>
                        <div>
                            <span className="flex-1 ml-3 whitespace-nowrap">Украшения </span>
                        </div>
                    </li>

                    <li>

                    </li>
                </ul>
            </div>
        </aside>
    );
};

export default Sidebar;