import * as React from 'react';
import {Box, Divider, Drawer, List, ListItem, ListItemText, Toolbar} from "@mui/material";
import ListItemButton from "@mui/material/ListItemButton";


export const Sidebar: React.FC = (): JSX.Element => {

    const drawerWidth = 250;
    const types = [
        {
            title: 'Toys',
            path: '/products/type/toys'
        },
        {
            title: 'Wings',
            path: '/products/type/wings'
        },
        {
            title: 'Mobiles',
            path: '/products/type/mobiles'
        },
        {
            title: 'Decorations',
            path: '/products/type/decorations'
        }
    ];
 return (
     <div>
           <Box>
             <Box
             >
                 <Drawer
                     variant="permanent"
                     sx={{
                         display: {xs: 'none', sm: 'block'},
                         '& .MuiDrawer-paper': {boxSizing: 'border-box', width: drawerWidth}
                     }}
                 >
                     <Toolbar/>
                     <Divider/>
                     <List>
                         {types.map((item) => (
                             <ListItem key={item.title} disablePadding>
                                 <ListItemButton href={item.path}>
                                     <ListItemText primary={item.title}/>
                                 </ListItemButton>
                             </ListItem>
                         ))}
                     </List>
                 </Drawer>
             </Box>
         </Box>
     </div>
    );
};



