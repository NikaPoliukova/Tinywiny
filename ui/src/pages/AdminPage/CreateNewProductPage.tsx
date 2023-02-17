import React, {useState} from 'react'
import {Form, Header, Icon, Segment} from 'semantic-ui-react'
import {IconButton} from "@mui/material";
import {AddAPhoto} from "@mui/icons-material";
import {Product} from "../../model/Product";
import ProductService from "../../services/ProductService";
import {useNavigate} from 'react-router-dom';
import MyHeader from 'pages/component/MyHeader';
import {Image} from "../../model/Image";
import ImageService from "../../services/ImageService";

const getTypeProduct = [
    {key: 'm', text: 'mobil', value: 'male'},
    {key: 'f', text: 'toy', value: 'female'},
    {key: 'o', text: 'Other', value: 'other'},
]

export function CreateProduct() {

    const [productName, setProductName] = useState('');
    const [price, setPrice] = useState(0);
    const [countInStock, setCountInStock] = useState(0);
    const [description, setDescription] = useState('');
    const [idType, setIdType] = useState(0);
    const [productId, setProductId] = useState(0);
    const [imageName, setImageName] = useState('');

    const navigate = useNavigate();
    const addProduct = () => {
        const product: Product = {
            productName,
            price,
            countInStock,
            description,
            idType
            //я хочу иметь возможность загружать сразу фото и нет,нужно два метода?
        };
        ProductService.createProduct(product).then(response => navigate("/products"));
    }
    const addImage = () => {
        const image : Image ={
            imageName,
           productId
            // Хочу сразу загружать фото,но нету поля картинки(id продукт)
                   }
        ImageService.updateImage(image).then(response => navigate("/products"));
    }

    return (
        <>
            <MyHeader />
            <Segment placeholder>
                <Header icon>
                    <Icon name='photo'/>
                </Header>
                <IconButton color="primary"
                            aria-label="upload picture"
                            component="label"
                            onClick={addImage}
                >
                    <input hidden accept="image/*" type="file"
                          // value={file}
                          // onChange={e => setFile(e.target.value)}
                    />
                    <AddAPhoto/>
                </IconButton>
            </Segment>
            <Form>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='Product Name' placeholder='Product Name'
                                value={productName}
                                onChange={e => setProductName(e.target.value)}/>
                </Form.Group>
                <Form.Group widths='equal'>
                    <Form.Select
                        fluid
                        label='Type product'
                        options={getTypeProduct}
                        placeholder='Type product'
                        value={idType}
                       // onChange={e => setIdType(Number(e.target.value))}
                    />
                </Form.Group>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='Price' placeholder='Price' value={price}
                                onChange={e => setPrice(Number(e.target.value))}/>
                </Form.Group>
                <Form.Group widths='equal'>
                    <Form.Input fluid label='Count in stock' placeholder='Count in stock'
                                value={countInStock}
                                onChange={e => setCountInStock(Number(e.target.value))}/>
                </Form.Group>
                <Form.Group widths='equal'>
                </Form.Group>
                <Form.TextArea label='Discription' placeholder='Write dicscription...'
                               value={description}
                               onChange={e => setDescription(e.target.value)}/>

                <Form.Button
                    onClick={addProduct}
                >Add product</Form.Button>
            </Form>
        </>
    )
}


